package com.example.demo.core.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.UUID;

/**
 * Lớp cơ sở trừu tượng cho tất cả các thực thể trong hệ thống.
 * Cung cấp các trường và chức năng chung cho tất cả thực thể.
 */
@Getter
@Setter
@MappedSuperclass // Chỉ định đây là lớp cha của các thực thể JPA (không phải thực thể độc lập)
@EntityListeners(CoreEntityListener.class) // Xác định bộ lắng nghe vòng đời thực thể
public abstract class CoreEntity {

    /**
     * Phương thức trừu tượng để lấy tiền tố cho mã số noCode.
     * Các lớp thực thể cụ thể phải triển khai phương thức này.
     */
    public abstract String getPrefixNoCode();

    /**
     * Trường tạm thời để giữ ID của người dùng hiện tại trong quá trình thao tác.
     * Không được lưu vào cơ sở dữ liệu.
     */
    @Transient
    @JsonIgnore // Loại bỏ khỏi quá trình chuyển đổi JSON
    private UUID currentUserId;

    /**
     * Khóa chính sử dụng chiến lược sinh UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID COMMENT 'ID'")
    private UUID id;

    /**
     * Mã định danh nghiệp vụ cho bản ghi.
     */
    @Column(name = "no_code", length = 50, nullable = true, columnDefinition = "VARCHAR(50) COMMENT 'Mã số bản ghi'")
    private String noCode;

    /**
     * Tiêu đề chính của bản ghi theo ngôn ngữ mặc định.
     */
    @Column(name = "title", length = 255, nullable = true, columnDefinition = "VARCHAR(255) COMMENT 'Tiêu đề'")
    private String title;

    /**
     * Tiêu đề bản ghi bằng tiếng Anh.
     */
    @Column(name = "title_en", length = 255, nullable = true, columnDefinition = "VARCHAR(255) COMMENT 'Tiêu đề (tiếng Anh)'")
    private String titleEn;

    /**
     * Thứ tự sắp xếp của bản ghi.
     */
    @Column(name = "sn", nullable = true, columnDefinition = "INT DEFAULT 0 COMMENT 'Thứ tự sắp xếp'")
    private Integer sn = 0;

    /**
     * Trạng thái của bản ghi (Active/Inactive/...).
     */
    @Column(name = "status", length = 20, nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'Active' COMMENT 'Trạng thái'")
    private String status = "Active";

    /**
     * ID của người tạo bản ghi.
     * Được tự động gán bởi Spring Data.
     */
    @CreatedBy
    @Column(name = "created_by_id", nullable = true, columnDefinition = "UUID COMMENT 'ID người tạo'")
    private UUID createdById;

    /**
     * Thời gian tạo bản ghi.
     * Được tự động gán bởi Spring Data.
     */
    @CreatedDate
    @Column(name = "created_date", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày tạo'")
    private Date createdDate;

    /**
     * ID của người chỉnh sửa cuối cùng.
     * Được tự động gán bởi Spring Data.
     */
    @LastModifiedBy
    @Column(name = "last_modified_by_id", nullable = true, columnDefinition = "UUID COMMENT 'ID người chỉnh sửa cuối cùng'")
    private UUID lastModifiedById;

    /**
     * Thời gian chỉnh sửa lần cuối.
     * Được tự động gán bởi Spring Data.
     */
    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Ngày chỉnh sửa cuối cùng'")
    private Date lastModifiedDate;

    /**
     * Cờ đánh dấu bản ghi đã bị xóa mềm (soft delete).
     */
    @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT 'Đã xóa'")
    private Boolean isDelete = false;

    /**
     * ID của người đã xóa bản ghi.
     */
    @Column(name = "deleted_by_id", nullable = true, columnDefinition = "UUID COMMENT 'ID người xóa'")
    private UUID deletedById;

    /**
     * Thời gian xóa bản ghi.
     */
    @Column(name = "deleted_date", nullable = true, columnDefinition = "TIMESTAMP COMMENT 'Ngày xóa'")
    private Date deletedDate;

    /**
     * Cờ đánh dấu bản ghi này có phải là bản ghi cha trong hệ thống phân cấp hay không.
     */
    @Column(name = "is_parent", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT 'Có phải bản ghi cha không'")
    private Boolean isParent = false;

    /**
     * Cấp bậc trong hệ thống phân cấp.
     */
    @Column(name = "lvl", nullable = false, columnDefinition = "INT DEFAULT 0 COMMENT 'Cấp bậc'")
    private Integer lvl = 0;

    /**
     * ID bản ghi cha trong hệ thống phân cấp.
     */
    @Column(name = "parent_id", nullable = true, columnDefinition = "UUID COMMENT 'ID bản ghi cha'")
    private UUID parentId;

    /**
     * Số thứ tự phụ để sắp xếp.
     */
    @Column(name = "sub_sn", nullable = true, columnDefinition = "INT DEFAULT 0 COMMENT 'Thứ tự phụ'")
    private Integer subSn = 0;

    /**
     * Phân loại loại đối tượng.
     */
    @Column(name = "item_type", length = 50, nullable = true, columnDefinition = "VARCHAR(50) COMMENT 'Loại đối tượng'")
    private String itemType;

    /**
     * Mô tả chi tiết bản ghi.
     */
    @Column(name = "description", columnDefinition = "TEXT COMMENT 'Mô tả'")
    private String description;

    /**
     * Ghi chú bổ sung về bản ghi.
     */
    @Column(name = "note", columnDefinition = "TEXT COMMENT 'Ghi chú'")
    private String note;
}
