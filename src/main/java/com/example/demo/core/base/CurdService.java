package com.example.demo.core.base;

import com.example.demo.core.constants.HttpStatusMessage;
import com.example.demo.core.exception.BusinessException;
import com.example.demo.core.utils.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class CurdService<T extends BaseEntity> {
    protected final BaseRepository<T> repository;
    protected ModelMapper modelMapper;

    protected CurdService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Autowired
    void setterInjector(
            ModelMapper modelMapper
    ) {
        this.modelMapper = modelMapper;
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatusMessage.DATA_NOT_FOUND));
    }

    public T create(BaseDto baseDto) {
        T t = modelMapper.map(baseDto, getEntityClass());
        return repository.save(t);
    }

    public T update(UUID id, BaseDto baseDto) {
        var t = repository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatusMessage.DATA_NOT_FOUND, Map.of("fields", id.toString())));
        MapperUtils.mapNonNullFields(baseDto, t);
        return repository.save(t);
    }

    public void delete(UUID id) {
        var t = repository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatusMessage.DATA_NOT_FOUND, Map.of("fields", id.toString())));
        repository.delete(t);
    }

    /**
     * Gets the class of the generic type parameter T
     */
    @SuppressWarnings("unchecked")
    private Class<T> getEntityClass() {
        // Get the generic superclass
        Type type = getClass().getGenericSuperclass();

        // Handle case when extended through another abstract class
        while (!(type instanceof ParameterizedType parameterizedType) ||
                !CurdService.class.equals(parameterizedType.getRawType())
        ) {
            if (type instanceof ParameterizedType parameterizedType) {
                type = ((Class<?>) parameterizedType.getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        // Get the actual type argument (T)
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
}
