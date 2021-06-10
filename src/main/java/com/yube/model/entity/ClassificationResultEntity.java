package com.yube.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "classification_results")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClassificationResultEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    private long recordTime;

    private String result;
}
