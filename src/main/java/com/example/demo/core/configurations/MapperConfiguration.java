package com.example.demo.core.configurations;

import com.example.demo.core.utils.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return MapperUtils.getDefaultMapper();
    }


}
