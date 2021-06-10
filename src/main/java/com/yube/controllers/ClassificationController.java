package com.yube.controllers;

import com.yube.model.dto.*;
import com.yube.services.ClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/classification")
public class ClassificationController {

    private final ClassificationService classificationService;

    @GetMapping("/classes")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ClassesResponse getClasses() {
        return classificationService.getClasses();
    }

    @GetMapping("/load-image/{className}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public LoadResponse loadImage(@PathVariable("className") String className) {
        return classificationService.loadImage(className);
    }

    @GetMapping("/classification-statistic")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ClassificationStatistic getClassificationStatistic(@RequestParam("userId") String userId,
                                                              @RequestParam(value = "startDate", required = false) Long startDate,
                                                              @RequestParam(value = "endDate", required = false) Long endDate) {
        return classificationService.getClassificationStatistic(UUID.fromString(userId), startDate, endDate);
    }

    @PostMapping("/upload-image")
    @PreAuthorize("hasRole('ADMIN')")
    public void uploadImage(@RequestBody UploadRequest uploadRequest) {
        classificationService.uploadImage(uploadRequest);
    }

    @PostMapping("/classify")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ClassificationResponse classify(@RequestBody ClassificationRequest classificationRequest) {
        return classificationService.classify(classificationRequest);
    }

    @GetMapping("/temp-dataset-info")
    @PreAuthorize("hasRole('ADMIN')")
    public TempDatasetInfo getTempDatasetInfo() {
        return classificationService.getTempDatasetInfo();
    }
}
