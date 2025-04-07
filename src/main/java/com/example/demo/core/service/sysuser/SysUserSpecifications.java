package com.example.demo.core.service.sysuser;

import com.example.demo.core.entities.SysUser;
import jakarta.persistence.criteria.JoinType;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

@UtilityClass
public class SysUserSpecifications {

    Specification<SysUser> hasSearchTerm(String searchTerm) {
        return (root, query, cb) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return cb.conjunction();
            }

            String pattern = "%" + searchTerm.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("userName")), pattern),
                    cb.like(cb.lower(root.get("email")), pattern),
                    cb.like(cb.lower(root.get("phoneMobile")), pattern));
        };
    }

    Specification<SysUser> modifiedAfter(Instant startTime) {
        return (root, query, cb) -> {
            if (startTime == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get("lastModifiedDate"), startTime);
        };
    }

    Specification<SysUser> modifiedBefore(Instant endTime) {
        return (root, query, cb) -> {
            if (endTime == null) {
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(root.get("lastModifiedDate"), endTime);
        };
    }

    static Specification<SysUser> withRoles() {
        return (root, query, cb) -> {
            // Chỉ fetch khi query cho User entity
            if (query == null || query.getResultType() == null) {
                return cb.conjunction();
            }

            if (query.getResultType() == SysUser.class || query.getResultType() == Object.class) {
                root.fetch("sysUsersRoles", JoinType.LEFT);
            }

            return cb.conjunction();
        };
    }

}
