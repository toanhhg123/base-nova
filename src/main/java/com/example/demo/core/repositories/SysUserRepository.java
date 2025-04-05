package com.example.demo.core.repositories;

import com.example.demo.core.base.BaseRepository;
import com.example.demo.core.entities.SysUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUserRepository extends BaseRepository<SysUser> {
    @Query("SELECT u FROM SysUser u LEFT JOIN FETCH u.sysUsersRoles")
    List<SysUser> findAllWithFetch();
}
