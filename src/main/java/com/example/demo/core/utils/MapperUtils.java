package com.example.demo.core.utils;

import lombok.experimental.UtilityClass;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@UtilityClass
public class MapperUtils {
    private final ModelMapper mapperNonNull = getNonNullMapper();

    private ModelMapper getNonNullMapper() {
        ModelMapper nonNullMapper = new ModelMapper();
        nonNullMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setMatchingStrategy(MatchingStrategies.STRICT)
        ;
        return nonNullMapper;
    }

    public void mapNonNullFields(Object source, Object destination) {
        mapperNonNull.map(source, destination);
    }

    public ModelMapper getDefaultMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                // Bỏ qua các trường hợp không rõ ràng khi ánh xạ
                .setAmbiguityIgnored(true)
                // Bỏ qua các giá trị null khi ánh xạ
                .setSkipNullEnabled(true)
                // Đảm bảo chỉ ánh xạ khi giá trị nguồn không phải null
                .setPropertyCondition(Conditions.isNotNull())
                // Cho phép truy cập trực tiếp vào các trường (field)
                .setFieldMatchingEnabled(true)
                // Cho phép truy cập vào các trường private
                // Sử dụng chiến lược ánh xạ nghiêm ngặt để tránh ánh xạ sai
                .setMatchingStrategy(MatchingStrategies.STRICT)
                // Thêm điều kiện để chỉ ánh xạ khi nguồn có giá trị
                .setPropertyCondition(ctx -> ctx.getSource() != null);
        return modelMapper;
    }
}
