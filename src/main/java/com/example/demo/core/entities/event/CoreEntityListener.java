package com.example.demo.core.entities.event;

import com.example.demo.core.context.GlobalUserContextService;
import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.utils.CodeGenerationUtils;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;


/**
 * {@code @PrePersist:}: Trước khi entity được lưu lần đầu
 * <p>
 * {@code @PostPersist:} Sau khi entity được lưu lần đầu
 * <p>
 * {@code @PreUpdate:} Trước khi entity được cập nhật
 * <p>
 * {@code @PostUpdate:} Sau khi entity được cập nhật
 * <p>
 * {@code @PreRemove:} Trước khi entity bị xóa
 * <p>
 * {@code @PostRemove:} Sau khi entity bị xóa
 * <p>
 * {@code @PostLoad:} Sau khi entity được load từ DB
 */
@Component
@AllArgsConstructor
public class CoreEntityListener {
    private final GlobalUserContextService userContextService;


    /**
     * Callback method executed before a Java Persistence API (JPA) entity is persisted to the database.
     * Initializes creation and last modification dates, and sets the creator's ID.
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        // Set creation and last modification dates to the current date and time
        entity.setCreatedDate(new Date());
        entity.setLastModifiedDate(new Date());

        // Add no code
        var noCode = entity.getPrefixNoCode().prefixNoCode;
        entity.setNoCode(CodeGenerationUtils.genCodeWithTime(noCode));

        // Retrieve the current user's ID from the user context service
        var userId = userContextService.getUserId();

        // Set the creator's ID
        entity.setCreatedById(userId);
        entity.setLastModifiedById(userId);
    }

    /**
     * Callback method executed before a Java Persistence API (JPA) entity is updated to the database.
     * Updates the last modification date to the current date and time.
     * Also sets the modifier's ID to the current user's ID if the user is signed in.
     *
     * @param entity the entity to be updated
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        // Set last modification date to the current date and time
        entity.setLastModifiedDate(new Date());

        // Retrieve the current user's ID from the user context service
        var userId = userContextService.getUserId();
        if (Objects.isNull(userId)) return;
        entity.setLastModifiedById(userId);
    }

    /**
     * Callback method executed after a Java Persistence API (JPA) entity is loaded from the database.
     * Sets the current user's ID to the entity's transient current user ID field.
     * <p>
     * This is necessary because the current user's ID is not stored in the database and is only
     * available during the request context.
     *
     * @param entity the entity that has been loaded from the database
     */
    @PostLoad
    public void postLoad(BaseEntity entity) {
        entity.setCurrentUserId(userContextService.getUserId());
    }

    /**
     * Callback method executed before a Java Persistence API (JPA) entity is removed from the database.
     * Sets the deletion date to the current date and time, marks the entity as deleted, and assigns
     * the ID of the current user as the one who deleted the entity.
     *
     * @param entity the entity that is about to be removed from the database
     */
    @PreRemove
    public void preRemove(BaseEntity entity) {
        entity.setDeletedDate(new Date());
        entity.setIsDelete(true);

        var userId = userContextService.getUserId();
        entity.setDeletedById(userId);
    }
}
