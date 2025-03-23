package com.task.JSON.service;

import com.task.JSON.dto.DatasetRecordDTO;
import com.task.JSON.dto.QueryResponseDTO;
import com.task.JSON.model.DatasetRecord;
import com.task.JSON.respository.DatasetRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DatasetRecordServiceImpl implements DatasetRecordService{
    @Autowired
    private DatasetRecordRepository repository;

    @Override
    public DatasetRecord saveRecord(  String datasetName,Map<String, Object> flatData ) {
        DatasetRecord record = new DatasetRecord();
        record.setDatasetName(datasetName);
        record.setData(flatData);

        return repository.save(record);
    }

    @Override
    public QueryResponseDTO getRecords(String datasetName, String groupBy, String sortBy, String order) {
        List<DatasetRecord> records = repository.findByDatasetName(datasetName);
        QueryResponseDTO response = new QueryResponseDTO();

        if (groupBy != null) {
            Map<Object, List<Map<String, Object>>> grouped = records.stream()
                    .map(DatasetRecord::getData)
                    .collect(Collectors.groupingBy(data -> data.get(groupBy)));
            response.setGroupedRecords(grouped);
        }

        if (sortBy != null) {
            List<Map<String, Object>> sorted = records.stream()
                    .map(DatasetRecord::getData)
                    .sorted((a, b) -> {
                        Comparable valueA = (Comparable) a.get(sortBy);
                        Comparable valueB = (Comparable) b.get(sortBy);
                        return "asc".equalsIgnoreCase(order) ? valueA.compareTo(valueB) : valueB.compareTo(valueA);
                    })
                    .collect(Collectors.toList());
            response.setSortedRecords(sorted);
        }

        return response;
    }
}
