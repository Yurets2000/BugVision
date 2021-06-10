package com.yube.model.mapping;

import com.yube.model.dto.ClassificationResult;
import com.yube.model.entity.ClassificationResultEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {StringToIdMapper.class}, componentModel = "spring")
public abstract class ClassificationResultMapper implements BaseMapper<ClassificationResultEntity, ClassificationResult> {
    @Override
    @Mappings({
            @Mapping(source = "user.id", target = "userId")
    })
    public abstract ClassificationResult map(ClassificationResultEntity source);

    @Override
    @Mappings({
            @Mapping(source = "userId", target = "user.id")
    })
    public abstract ClassificationResultEntity unmap(ClassificationResult source);
}
