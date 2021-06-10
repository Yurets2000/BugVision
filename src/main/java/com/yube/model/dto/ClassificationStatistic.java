package com.yube.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassificationStatistic extends HashMap<String, ClassificationStatisticItem> {

}
