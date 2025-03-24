package com.task.JSON.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.JSON.dto.DatasetRecordDTO;
import com.task.JSON.model.DatasetRecord;
import com.task.JSON.service.DatasetRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class DatasetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatasetRecordService datasetRecordService;

    @Test
    void shouldInsertRecordSuccessfully() throws Exception {
        // Given
        DatasetRecordDTO dto = new DatasetRecordDTO();
        dto.setDatasetName("employee_dataset");
        dto.setData(Map.of("id", 1, "name", "John Doe", "age", 30));

        DatasetRecord mockRecord = new DatasetRecord();
        mockRecord.setId(1L);
        mockRecord.setDatasetName("employee_dataset");
        mockRecord.setData(dto.getData());

        when(datasetRecordService.saveRecord(anyString(), any(Map.class))).thenReturn(mockRecord);

        // When + Then
        mockMvc.perform(post("/api/dataset/employee_dataset/record") // Use PathVariable format
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(dto.getData())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Record added successfully"))
                .andExpect(jsonPath("$.dataset").value("employee_dataset"))
                .andExpect(jsonPath("$.recordId").value(1));
    }
}
