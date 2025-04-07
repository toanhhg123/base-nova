package com.example.demo.core.repositories;

import com.example.demo.core.base.BaseRepository;
import com.example.demo.core.entities.SysResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface SysResourceRepository extends BaseRepository<SysResource> {

    /**
     * Custom native query to find all SysResource entries accessible by a user, either directly or via roles.
     * Supports pagination using a main query and a count query.
     *
     * @param userId   the UUID of the user whose accessible resources are to be fetched.
     * @param type     the type of resource to filter (e.g., "MENU").
     * @param pageable pagination and sorting configuration.
     * @return a Page of SysResource entities.
     */
    @Query(
            value = """
                    SELECT * FROM sys_resources sr
                    WHERE sr.id IN (
                        SELECT sur.resource_id
                        FROM sys_users_resources sur
                        WHERE sur.user_id = :userId
                          AND sur.is_delete = false
                        UNION
                        SELECT srr.resource_id
                        FROM sys_users_roles sur
                            JOIN sys_roles sr ON sr.id = sur.role_id
                            JOIN sys_roles_resources srr ON srr.role_id = sr.id AND srr.is_delete = false
                        WHERE sur.user_id = :userId
                          AND sur.is_delete = false
                    )
                    AND sr.is_delete = false
                    AND sr.item_type = :type
                    """,
            countQuery = """
                    SELECT COUNT(*) FROM sys_resources sr
                    WHERE sr.id IN (
                        SELECT sur.resource_id
                        FROM sys_users_resources sur
                        WHERE sur.user_id = :userId
                          AND sur.is_delete = false
                        UNION
                        SELECT srr.resource_id
                        FROM sys_users_roles sur
                            JOIN sys_roles sr ON sr.id = sur.role_id
                            JOIN sys_roles_resources srr ON srr.role_id = sr.id AND srr.is_delete = false
                        WHERE sur.user_id = :userId
                          AND sur.is_delete = false
                    )
                    AND sr.is_delete = false
                    AND sr.item_type = :type
                    """,
            nativeQuery = true)
    Page<SysResource> findAllResourcesByUserId(
            UUID userId,
            String type,
            Pageable pageable
    );
}
