package com.yube.repositories;

import com.yube.model.entity.ClassificationResultEntity;

import java.util.List;
import java.util.UUID;

public interface ClassificationResultRepository extends BaseRepository<ClassificationResultEntity> {
    List<ClassificationResultEntity> getClassificationResultEntitiesByUserIdAndRecordTimeBetween(UUID userId,
                                                                                                 long start,
                                                                                                 long end);
}
