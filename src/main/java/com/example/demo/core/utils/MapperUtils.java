package com.example.demo.core.utils;

import lombok.experimental.UtilityClass;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

@UtilityClass
public class MapperUtils {
    private final ModelMapper mapperNonNull = getNonNullMapper();

    private ModelMapper getNonNullMapper() {
        ModelMapper nonNullMapper = new ModelMapper();
        nonNullMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return nonNullMapper;
    }

    public void mapNonNullFields(Object source, Object destination) {
        mapperNonNull.map(source, destination);
    }
}
