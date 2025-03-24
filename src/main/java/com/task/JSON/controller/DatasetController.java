package com.task.JSON.controller;

import com.task.JSON.dto.QueryResponseDTO;
import com.task.JSON.model.DatasetRecord;
import com.task.JSON.service.DatasetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dataset")
public class DatasetController {

    @Autowired
    private DatasetRecordService service;


    @PostMapping("/{datasetName}/record")
    public ResponseEntity<?> insertRecord(
           @PathVariable String datasetName,
            @RequestBody Map<String, Object> flatData) {

        DatasetRecord record = service.saveRecord(datasetName, flatData);
        return ResponseEntity.ok(Map.of(
                "message", "Record added successfully",
                "dataset", datasetName,
                "recordId", record.getId()
        ));
    }


    @GetMapping("/{datasetName}/query")
    public ResponseEntity<?> queryRecords(
            @PathVariable String datasetName,
            @RequestParam(required = false) String groupBy,
             @RequestParam(required = false) String sortBy,
             @RequestParam(required = false) String order) {

        QueryResponseDTO response = service.getRecords(datasetName, groupBy, sortBy, order);
        return ResponseEntity.ok(response);
    }
}
