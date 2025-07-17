package com.algaworks.algasensors.device.management.api.client;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

// Foi comenatado para a criação da classe ApiExceptionHandler
//@ResponseStatus(BAD_GATEWAY)
public class SensorMonitoringClientBadGatewayException extends RuntimeException {
}
