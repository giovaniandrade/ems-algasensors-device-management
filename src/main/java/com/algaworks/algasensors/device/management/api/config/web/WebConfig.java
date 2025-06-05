package com.algaworks.algasensors.device.management.api.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Esta classe pode ser usada para configurar aspectos adicionais do Spring MVC, se necessário.
    // Por exemplo, você pode adicionar interceptadores, manipuladores de exceção, etc.

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToTSIDWebConverter());
    }
}
