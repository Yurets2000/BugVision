package com.yube.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TempDatasetInfo {
    private String samplesLoaded;
    private String samplesNeededToNextUpdate;
}
