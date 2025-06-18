package com.algaworks.algasensors.device.management.api.model;

import lombok.Data;

@Data
public class SensorAlertInput {
    private Double maxTemperature;
    private Double minTemperature;
}
