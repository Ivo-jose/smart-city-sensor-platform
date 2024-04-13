package br.com.ivogoncalves.smartcitysensorplatform.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
     ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
