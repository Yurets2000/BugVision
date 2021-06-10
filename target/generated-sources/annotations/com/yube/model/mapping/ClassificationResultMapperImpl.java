package com.yube.model.mapping;

import com.yube.model.dto.ClassificationResult;
import com.yube.model.entity.ClassificationResultEntity;
import com.yube.model.entity.UserEntity;
import java.util.UUID;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-20T00:36:01+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class ClassificationResultMapperImpl extends ClassificationResultMapper {

    @Autowired
    private StringToIdMapper stringToIdMapper;

    @Override
    public ClassificationResult map(ClassificationResultEntity source) {
        if ( source == null ) {
            return null;
        }

        ClassificationResult classificationResult = new ClassificationResult();

        classificationResult.setUserId( stringToIdMapper.idToString( sourceUserId( source ) ) );
        classificationResult.setId( stringToIdMapper.idToString( source.getId() ) );
        classificationResult.setRecordTime( source.getRecordTime() );
        classificationResult.setResult( source.getResult() );

        return classificationResult;
    }

    @Override
    public ClassificationResultEntity unmap(ClassificationResult source) {
        if ( source == null ) {
            return null;
        }

        ClassificationResultEntity classificationResultEntity = new ClassificationResultEntity();

        classificationResultEntity.setUser( classificationResultToUserEntity( source ) );
        classificationResultEntity.setId( stringToIdMapper.stringToId( source.getId() ) );
        classificationResultEntity.setRecordTime( source.getRecordTime() );
        classificationResultEntity.setResult( source.getResult() );

        return classificationResultEntity;
    }

    private UUID sourceUserId(ClassificationResultEntity classificationResultEntity) {
        if ( classificationResultEntity == null ) {
            return null;
        }
        UserEntity user = classificationResultEntity.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserEntity classificationResultToUserEntity(ClassificationResult classificationResult) {
        if ( classificationResult == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( stringToIdMapper.stringToId( classificationResult.getUserId() ) );

        return userEntity;
    }
}
