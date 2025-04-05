package com.example.demo.core.service.sysuser;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.response.SysUserResponse;
import com.example.demo.core.entities.SysUser;
import com.example.demo.core.repositories.SysUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl extends CurdService<SysUser> implements SysUserService {
    private final SysUserRepository sysUserRepository;

    protected SysUserServiceImpl(SysUserRepository repository) {
        super(repository);
        this.sysUserRepository = repository;
    }

    @Override
    public SysUserResponse findById(UUID id) {
        var user = super.getById(id);
        return modelMapper.map(user, SysUserResponse.class);
    }

    @Override
    public List<SysUserResponse> findAll() {
        var users = sysUserRepository.findAllWithFetch();
        return users.stream()
                .map(user -> modelMapper.map(user, SysUserResponse.class))
                .toList();
    }
}
