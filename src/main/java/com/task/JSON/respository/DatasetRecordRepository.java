package com.task.JSON.respository;

import com.task.JSON.model.DatasetRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatasetRecordRepository extends JpaRepository<DatasetRecord,Long> {
    List<DatasetRecord> findByDatasetName(String datasetName);
}
