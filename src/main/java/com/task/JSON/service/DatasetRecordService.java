package com.task.JSON.service;

import com.task.JSON.dto.DatasetRecordDTO;
import com.task.JSON.dto.QueryResponseDTO;
import com.task.JSON.model.DatasetRecord;

import java.util.Map;

public interface DatasetRecordService {
    DatasetRecord saveRecord(String datasetName, Map<String, Object> flatData );
    QueryResponseDTO getRecords(String datasetName, String groupBy, String sortBy, String order);
}
