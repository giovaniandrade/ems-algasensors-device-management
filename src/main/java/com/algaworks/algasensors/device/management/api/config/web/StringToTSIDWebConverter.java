package com.algaworks.algasensors.device.management.api.config.web;

import io.hypersistence.tsid.TSID;
import org.springframework.core.convert.converter.Converter;

// Esse conversor é necessário para converter uma String em TSID quando o Spring MVC recebe uma String como parâmetro de rota ou query string.
public class StringToTSIDWebConverter implements Converter<String, TSID> {

    @Override
    public TSID convert(String source) {
        return TSID.from(source);
    }
}
