package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Entity
@Table(name = "sys_settings_reports")
public class SysSettingReport extends BaseEntity {
    @Column(name = "sql_text", length = Integer.MAX_VALUE)
    private String sqlText;

    @Column(name = "col_name", length = Integer.MAX_VALUE)
    private String colName;

    @Column(name = "col_title", length = Integer.MAX_VALUE)
    private String colTitle;

    @Column(name = "col_import", length = Integer.MAX_VALUE)
    private String colImport;

    @Column(name = "col_import_save", length = Integer.MAX_VALUE)
    private String colImportSave;

    @Column(name = "col_import_col_map", length = Integer.MAX_VALUE)
    private String colImportColMap;

    @Column(name = "col_export", length = Integer.MAX_VALUE)
    private String colExport;

    @Column(name = "col_export_save", length = Integer.MAX_VALUE)
    private String colExportSave;

    @Column(name = "col_export_col_map", length = Integer.MAX_VALUE)
    private String colExportColMap;

    @Column(name = "col_export_title", length = Integer.MAX_VALUE)
    private String colExportTitle;

    @Column(name = "src_sql", length = Integer.MAX_VALUE)
    private String srcSql;

    @Column(name = "src_col", length = Integer.MAX_VALUE)
    private String srcCol;

    @Column(name = "src_col_map", length = Integer.MAX_VALUE)
    private String srcColMap;

    @Column(name = "src_col_title", length = Integer.MAX_VALUE)
    private String srcColTitle;

    @Column(name = "src_col_import", length = Integer.MAX_VALUE)
    private String srcColImport;

    @Column(name = "src_col_import_col_map", length = Integer.MAX_VALUE)
    private String srcColImportColMap;

    @Column(name = "src_col_import_save", length = Integer.MAX_VALUE)
    private String srcColImportSave;

    @Column(name = "src_col_export", length = Integer.MAX_VALUE)
    private String srcColExport;

    @Column(name = "src_col_export_col_map", length = Integer.MAX_VALUE)
    private String srcColExportColMap;

    @Column(name = "src_col_export_save", length = Integer.MAX_VALUE)
    private String srcColExportSave;

    @Column(name = "src_col_title_export", length = Integer.MAX_VALUE)
    private String srcColTitleExport;

    @ColumnDefault("false")
    @Column(name = "is_update", nullable = false)
    private Boolean isUpdate = false;

    @ColumnDefault("false")
    @Column(name = "is_condition", nullable = false)
    private Boolean isCondition = false;

    @Column(name = "update_sql", length = Integer.MAX_VALUE)
    private String updateSql;

    @Column(name = "update_col", length = Integer.MAX_VALUE)
    private String updateCol;

    @Column(name = "des_sql", length = Integer.MAX_VALUE)
    private String desSql;

    @Column(name = "des_col", length = Integer.MAX_VALUE)
    private String desCol;

    @Column(name = "des_table", length = Integer.MAX_VALUE)
    private String desTable;

    @Column(name = "des_col_map", length = Integer.MAX_VALUE)
    private String desColMap;

    @Column(name = "des_col_title", length = Integer.MAX_VALUE)
    private String desColTitle;

    @Column(name = "des_col_import", length = Integer.MAX_VALUE)
    private String desColImport;

    @Column(name = "des_col_import_col_map", length = Integer.MAX_VALUE)
    private String desColImportColMap;

    @Column(name = "des_col_import_save", length = Integer.MAX_VALUE)
    private String desColImportSave;

    @Column(name = "des_col_export", length = Integer.MAX_VALUE)
    private String desColExport;

    @Column(name = "des_col_export_col_map", length = Integer.MAX_VALUE)
    private String desColExportColMap;

    @Column(name = "des_col_export_save", length = Integer.MAX_VALUE)
    private String desColExportSave;

    @Column(name = "des_col_title_export", length = Integer.MAX_VALUE)
    private String desColTitleExport;

    @Column(name = "update_col_map", length = Integer.MAX_VALUE)
    private String updateColMap;

    @ColumnDefault("false")
    @Column(name = "is_des_delete", nullable = false)
    private Boolean isDesDelete = false;

    @Column(name = "des_delete_sql", length = Integer.MAX_VALUE)
    private String desDeleteSql;

    @Column(name = "des_delete_col", length = Integer.MAX_VALUE)
    private String desDeleteCol;

    @Column(name = "des_delete_col_map", length = Integer.MAX_VALUE)
    private String desDeleteColMap;

    @ColumnDefault("false")
    @Column(name = "is_show", nullable = false)
    private Boolean isShow = false;

    @ColumnDefault("false")
    @Column(name = "is_required", nullable = false)
    private Boolean isRequired = false;

    @ColumnDefault("false")
    @Column(name = "is_active_sheet", nullable = false)
    private Boolean isActiveSheet = false;

    @Column(name = "report_type", length = Integer.MAX_VALUE)
    private String reportType;

    @Column(name = "report_database", length = Integer.MAX_VALUE)
    private String reportDatabase;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_SETTING_REPORT;
    }
}
