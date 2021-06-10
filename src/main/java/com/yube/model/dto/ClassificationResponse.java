package com.yube.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationResponse extends HashMap<String, Double> {
}
