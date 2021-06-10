package com.yube.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationStatisticItem {
    private int classificationCount;
    @JsonIgnore
    private double sumPercentage;

    public double getAvgPercentage() {
        return sumPercentage / classificationCount;
    }
}
