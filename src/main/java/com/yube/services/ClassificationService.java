package com.yube.services;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.yube.model.dto.*;
import com.yube.model.entity.ClassificationResultEntity;
import com.yube.model.mapping.ClassificationResultMapper;
import com.yube.model.mapping.utils.MappingUtils;
import com.yube.repositories.ClassificationResultRepository;
import com.yube.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassificationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();
    private final UserRepository userRepository;
    private final ClassificationResultRepository classificationResultRepository;
    private final ClassificationResultMapper classificationResultMapper;
    @Value("${bug-vision.core}")
    private String baseUrl;

    public ClassesResponse getClasses() {
        String url = baseUrl + "/classes";
        return restTemplate.getForEntity(url, ClassesResponse.class).getBody();
    }

    public LoadResponse loadImage(String className) {
        long logStart = System.currentTimeMillis();
        String url = baseUrl + "/load-image/{className}";
        String image = restTemplate.getForEntity(url, String.class, className).getBody();
        LoadResponse loadResponse = new LoadResponse();
        loadResponse.setImage(image);
        long logEnd = System.currentTimeMillis();
        System.out.println("loadImage time: " + (logEnd - logStart));
        return loadResponse;
    }

    public void uploadImage(UploadRequest uploadRequest) {
        long logStart = System.currentTimeMillis();
        String url = baseUrl + "/upload-image";
        restTemplate.postForEntity(url, uploadRequest, Void.class);
        long logEnd = System.currentTimeMillis();
        System.out.println("uploadImage time: " + (logEnd - logStart));
    }

    public ClassificationResponse classify(ClassificationRequest classificationRequest) {
        long logStart = System.currentTimeMillis();
        String url = baseUrl + "/classify";
        ClassificationResponse response = restTemplate
                .postForEntity(url, classificationRequest, ClassificationResponse.class)
                .getBody();

        ClassificationResultEntity classificationResultEntity = new ClassificationResultEntity();
        if (classificationRequest.getUserId() != null) {
            classificationResultEntity.setUser(userRepository.findById(classificationRequest.getUserId()).get());
        }
        Type gsonType = new TypeToken<HashMap>() {
        }.getType();
        String gsonString = gson.toJson(response, gsonType);
        classificationResultEntity.setResult(gsonString);
        classificationResultEntity.setRecordTime(System.currentTimeMillis());
        classificationResultRepository.save(classificationResultEntity);
        long logEnd = System.currentTimeMillis();
        System.out.println("classify time: " + (logEnd - logStart));
        return response;
    }

    public ClassificationStatistic getClassificationStatistic(UUID userId, Long startDate, Long endDate) {
        long logStart = System.currentTimeMillis();
        long start;
        long end;
        if (startDate != null) {
            start = startDate;
        } else {
            start = 0;
        }
        if (endDate != null) {
            end = endDate;
        } else {
            end = System.currentTimeMillis();
        }
        List<ClassificationResult> classificationResultList = MappingUtils.mapAllList(
                classificationResultRepository.getClassificationResultEntitiesByUserIdAndRecordTimeBetween(userId, start, end),
                classificationResultMapper::map);
        ClassificationStatistic classificationStatistic = new ClassificationStatistic();
        for (ClassificationResult classificationResult : classificationResultList) {
            String result = classificationResult.getResult();
            Type gsonType = new TypeToken<HashMap>() {
            }.getType();
            HashMap<String, Double> resultMap = gson.fromJson(result, gsonType);
            String className = Collections.max(resultMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
            classificationStatistic.putIfAbsent(className, new ClassificationStatisticItem());
            classificationStatistic.computeIfPresent(className, (key, value) -> {
                value.setClassificationCount(value.getClassificationCount() + 1);
                value.setSumPercentage(value.getSumPercentage() + resultMap.get(className));
                return value;
            });
        }
        long logEnd = System.currentTimeMillis();
        System.out.println("classification statistic time: " + (logEnd - logStart));

        return classificationStatistic;
    }

    public TempDatasetInfo getTempDatasetInfo() {
        String url = baseUrl + "/temp-dataset-info";
        return restTemplate.getForEntity(url, TempDatasetInfo.class).getBody();
    }
}
