package com.example.demo.core.base;

import com.example.demo.core.constants.HttpStatusMessage;
import com.example.demo.core.exception.BusinessException;
import com.example.demo.core.utils.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;

public abstract class CurdService<T extends BaseEntity> {
    private final BaseRepository<T> baseRepository;
    private ModelMapper modelMapper;

    protected CurdService(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Autowired
    void setterInjector(
            ModelMapper modelMapper
    ) {
        this.modelMapper = modelMapper;
    }

    public T getById(UUID id) {
        return baseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatusMessage.DATA_NOT_FOUND));
    }

    public T create(BaseDto baseDto) {
        T t = modelMapper.map(baseDto, getEntityClass());
        return baseRepository.save(t);
    }

    public T update(UUID id, BaseDto baseDto) {
        var t = baseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatusMessage.DATA_NOT_FOUND));
        MapperUtils.mapNonNullFields(baseDto, t);
        return baseRepository.save(t);
    }

    public void delete(UUID id) {
        var t = baseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatusMessage.DATA_NOT_FOUND));
        baseRepository.delete(t);
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
