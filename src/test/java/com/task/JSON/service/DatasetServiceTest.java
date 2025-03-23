package com.task.JSON.service;

import com.task.JSON.dto.DatasetRecordDTO;
import com.task.JSON.model.DatasetRecord;
import com.task.JSON.respository.DatasetRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DatasetServiceTest {

    @InjectMocks
    private DatasetRecordServiceImpl datasetService;

    @Mock
    private DatasetRecordRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveRecordSuccessfully() {
        // Given
        DatasetRecordDTO dto = new DatasetRecordDTO();
        dto.setDatasetName("employee_dataset");
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("name", "John Doe");
        data.put("age", 30);
        data.put("department", "IT");
        dto.setData(data);

        DatasetRecord savedRecord = new DatasetRecord();
        savedRecord.setId(1L);
        savedRecord.setDatasetName("employee_dataset");
        savedRecord.setData(data);

        when(repository.save(any(DatasetRecord.class))).thenReturn(savedRecord);

        // When
        DatasetRecord result = datasetService.saveRecord(dto.getDatasetName(), dto.getData());

        // Then
        assertEquals("employee_dataset", result.getDatasetName());
        assertEquals(1, result.getData().get("id"));
        assertEquals("John Doe", result.getData().get("name"));

        verify(repository, times(1)).save(any(DatasetRecord.class));
    }


    @Test
    void shouldGroupByDepartmentSuccessfully() {
        // Given
        DatasetRecord record1 = new DatasetRecord();
        record1.setId(1L);
        record1.setDatasetName("employee_dataset");
        record1.setData(Map.of("id", 1, "name", "John", "department", "IT"));

        DatasetRecord record2 = new DatasetRecord();
        record2.setId(2L);
        record2.setDatasetName("employee_dataset");
        record2.setData(Map.of("id", 2, "name", "Jane", "department", "IT"));

        DatasetRecord record3 = new DatasetRecord();
        record3.setId(3L);
        record3.setDatasetName("employee_dataset");
        record3.setData(Map.of("id", 3, "name", "Alice", "department", "Marketing"));

        when(repository.findByDatasetName("employee_dataset")).thenReturn(
                java.util.List.of(record1, record2, record3)
        );

        // When
        var response = datasetService.getRecords("employee_dataset", "department", null, null);

        // Then
        assertEquals(2, response.getGroupedRecords().get("IT").size());
        assertEquals(1, response.getGroupedRecords().get("Marketing").size());
    }


    @Test
    void shouldSortByAgeAscending() {
        // Given
        DatasetRecord record1 = new DatasetRecord();
        record1.setData(Map.of("name", "John", "age", 30));

        DatasetRecord record2 = new DatasetRecord();
        record2.setData(Map.of("name", "Jane", "age", 25));

        DatasetRecord record3 = new DatasetRecord();
        record3.setData(Map.of("name", "Alice", "age", 28));

        when(repository.findByDatasetName("employee_dataset")).thenReturn(
                java.util.List.of(record1, record2, record3)
        );

        // When
        var response = datasetService.getRecords("employee_dataset", null, "age", "asc");

        // Then
        assertEquals("Jane", response.getSortedRecords().get(0).get("name"));
        assertEquals("Alice", response.getSortedRecords().get(1).get("name"));
        assertEquals("John", response.getSortedRecords().get(2).get("name"));
    }

}
