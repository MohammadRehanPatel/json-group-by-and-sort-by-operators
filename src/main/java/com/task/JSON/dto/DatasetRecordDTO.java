package com.task.JSON.dto;

import java.util.Map;
import java.util.HashMap;

public class DatasetRecordDTO {
    private String datasetName;
    private Map<String, Object> data;

    public DatasetRecordDTO() {
        this.data = new HashMap<>();
    }

    public void setDataFromFlatJson(Map<String, Object> flatData) {
        this.data.putAll(flatData); // Dynamically wraps incoming JSON as 'data'
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
