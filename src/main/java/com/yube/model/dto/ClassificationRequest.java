package com.yube.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationRequest {
    private UUID userId;
    private int resultsNum;
    private String image;
}
