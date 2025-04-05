package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "sys_import_logs")
public class SysImportLog extends BaseEntity {
    @Column(name = "file_name_original")
    private String fileNameOriginal;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path_upload")
    private String filePathUpload;

    @Column(name = "result_file")
    private String resultFile;

    @Column(name = "result_file_path")
    private String resultFilePath;

    @Column(name = "log_file")
    private String logFile;

    @Column(name = "log_file_path")
    private String logFilePath;

    @Column(name = "error_file")
    private String errorFile;

    @Column(name = "error_file_path")
    private String errorFilePath;

    @ColumnDefault("0")
    @Column(name = "total_rows")
    private Integer totalRows;

    @ColumnDefault("0")
    @Column(name = "total_processed")
    private Integer totalProcessed;

    @ColumnDefault("0")
    @Column(name = "total_errors")
    private Integer totalErrors;

    @Column(name = "process_status", length = 100)
    private String processStatus;

    @ColumnDefault("0")
    @Column(name = "progress")
    private Integer progress;

    @Column(name = "message", length = Integer.MAX_VALUE)
    private String message;

    @ColumnDefault("0")
    @Column(name = "step")
    private Integer step;

    @Column(name = "step_name")
    private String stepName;

    @Column(name = "log_kind", length = 100)
    private String logKind;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}