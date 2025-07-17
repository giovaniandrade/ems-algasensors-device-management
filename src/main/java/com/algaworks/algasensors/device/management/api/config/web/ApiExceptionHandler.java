package com.algaworks.algasensors.device.management.api.config.web;

import com.algaworks.algasensors.device.management.api.client.SensorMonitoringClientBadGatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;

// Antes da criação da classe, se desse timeout na chamada ao serviço remoto, retornnava "Internal Server Error"
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // Todas essas exceções herdam de Java.io.IOException
    // Assim nem todas as exceções de IO serão tratadas aqui, apenas essas 3
    @ExceptionHandler({
            SocketTimeoutException.class,
            ConnectException.class,
            ClosedChannelException.class
    })
    public ProblemDetail handler(IOException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);

        // problemDetail.setTitle("Gateway Timeout");
        problemDetail.setTitle("Erro de comunicação com o serviço remoto");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("/erros/gateway-timeout"));

        return problemDetail;
    }

    @ExceptionHandler(SensorMonitoringClientBadGatewayException.class)
    public ProblemDetail handler(SensorMonitoringClientBadGatewayException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);

        problemDetail.setTitle("Bad Gateway");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setType(URI.create("/erros/bad-gateway"));

        return problemDetail;
    }
}
