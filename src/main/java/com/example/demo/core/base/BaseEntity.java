package com.example.demo.core.base;


import com.example.demo.core.constants.SysDatasourceNoCode;
import com.example.demo.core.entities.event.CoreEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

/**
 * Lớp cơ sở trừu tượng cho tất cả các thực thể trong hệ thống.
 * Cung cấp các trường và chức năng chung cho tất cả thực thể.
 */
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@EntityListeners(CoreEntityListener.class)
public abstract class BaseEntity {
    /**
     * Khóa chính sử dụng chiến lược sinh UUID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID COMMENT 'ID'")
    UUID id;

    /**
     * Mã định danh nghiệp vụ cho bản ghi.
     */
    @Size(max = 50)
    @Column(name = "no_code", length = 50)
    private String noCode;

    /**
     * Tiêu đề chính của bản ghi theo ngôn ngữ mặc định.
     */
    @Size(max = 255)
    @Column(name = "title")
    private String title;

    /**
     * Tiêu đề bản ghi bằng tiếng Anh.
     */
    @Size(max = 255)
    @Column(name = "title_en")
    private String titleEn;


    /**
     * Thứ tự sắp xếp của bản ghi.
     */
    @ColumnDefault("0")
    @Column(name = "sn")
    private Integer sn;

    /**
     * Trạng thái của bản ghi (Active/Inactive/...).
     */
    @Size(max = 20)
    @NotNull
    @ColumnDefault("'Active'")
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    /**
     * ID của người tạo bản ghi.
     * Được tự động gán bởi Spring Data.
     */
    @Column(name = "created_by_id")
    private UUID createdById;

    /**
     * Thời gian tạo bản ghi.
     * Được tự động gán bởi Spring Data.
     */
    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    /**
     * ID của người chỉnh sửa cuối cùng.
     * Được tự động gán bởi Spring Data.
     */
    @Column(name = "last_modified_by_id")
    private UUID lastModifiedById;

    /**
     * Thời gian chỉnh sửa lần cuối.
     * Được tự động gán bởi Spring Data.
     */
    @NotNull
    @ColumnDefault("now()")
    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;
    /**
     * Cờ đánh dấu bản ghi đã bị xóa mềm (soft delete).
     */
    @NotNull
    @ColumnDefault("false")
    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete = false;

    /**
     * ID của người đã xóa bản ghi.
     */
    @Column(name = "deleted_by_id", length = Integer.MAX_VALUE)
    private String deletedById;

    /**
     * Thời gian xóa bản ghi.
     */
    @Column(name = "deleted_date")
    private Instant deletedDate;

    /**
     * Cờ đánh dấu bản ghi này có phải là bản ghi cha trong hệ thống phân cấp hay không.
     */
    @Column(name = "is_parent", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE COMMENT 'Có phải bản ghi cha không'")
    Boolean isParent = false;

    /**
     * Cấp bậc trong hệ thống phân cấp.
     */
    @NotNull
    @ColumnDefault("0")
    @Column(name = "lvl", nullable = false)
    private Integer lvl;

    /**
     * ID bản ghi cha trong hệ thống phân cấp.
     */
    @Column(name = "parent_id", length = Integer.MAX_VALUE)
    private String parentId;

    /**
     * Số thứ tự phụ để sắp xếp.
     */
    @ColumnDefault("0")
    @Column(name = "sub_sn")
    private Integer subSn;

    /**
     * Phân loại loại đối tượng.
     */
    @Size(max = 50)
    @Column(name = "item_type", length = 50)
    private String itemType;

    /**
     * Mô tả chi tiết bản ghi.
     */
    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    /**
     * Ghi chú bổ sung về bản ghi.
     */
    @Column(name = "note", length = Integer.MAX_VALUE)
    private String note;

    /**
     * Trường tạm thời để giữ ID của người dùng hiện tại trong quá trình thao tác.
     * Không được lưu vào cơ sở dữ liệu.
     */
    @Transient
    @JsonIgnore
    UUID currentUserId;

    /**
     * Phương thức trừu tượng để lấy tiền tố cho mã số noCode.
     * Các lớp thực thể cụ thể phải triển khai phương thức này.
     */
    public abstract SysDatasourceNoCode getPrefixNoCode();

}
