package com.task.JSON.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

    @JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryResponseDTO {

    private Map<Object, List<Map<String, Object>>> groupedRecords;
    private List<Map<String, Object>> sortedRecords;

    public Map<Object, List<Map<String, Object>>> getGroupedRecords() {
        return groupedRecords;
    }

    public void setGroupedRecords(Map<Object, List<Map<String, Object>>> groupedRecords) {
        this.groupedRecords = groupedRecords;
    }

    public List<Map<String, Object>> getSortedRecords() {
        return sortedRecords;
    }

    public void setSortedRecords(List<Map<String, Object>> sortedRecords) {
        this.sortedRecords = sortedRecords;
    }
}
