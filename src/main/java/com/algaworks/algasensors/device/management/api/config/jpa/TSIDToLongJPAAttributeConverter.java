package com.algaworks.algasensors.device.management.api.config.jpa;


import io.hypersistence.tsid.TSID;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/***
 * Necessário convert TSID para Long no JPA.
 * Funciona para atributos do tipo TSID em entiades JPA, mas não para identificadores.
 */
@Converter(autoApply = true) // Isso carrega automaticamente o conversor para todas as entidades que usam TSID, como um módulo do Jackson
public class TSIDToLongJPAAttributeConverter implements AttributeConverter<TSID, Long> {
    @Override
    public Long convertToDatabaseColumn(TSID attribute) {
        return attribute.toLong();
    }

    @Override
    public TSID convertToEntityAttribute(Long dbData) {
        return TSID.from(dbData);
    }
}
