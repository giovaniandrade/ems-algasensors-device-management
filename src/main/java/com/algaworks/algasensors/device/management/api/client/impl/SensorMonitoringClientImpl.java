package com.algaworks.algasensors.device.management.api.client.impl;

import com.algaworks.algasensors.device.management.api.client.SensorMonitoringClient;
import com.algaworks.algasensors.device.management.api.client.SensorMonitoringClientBadGatewayException;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringClientImpl(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://localhost:8082")
                .requestFactory(generateClientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::isError, ((request, response) -> {
                    // Aqui você pode tratar os erros de forma personalizada, se necessário
                    // Por exemplo, lançar uma exceção específica ou registrar o erro
                    // IMPORTANTE: Erros de Timeout não são tratados aqui
                    throw new SensorMonitoringClientBadGatewayException();
                }))
                .build();

    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(3)); // Tempo máximo de conexão com o serviço remoto
        factory.setReadTimeout(Duration.ofSeconds(5)); // Tempo máximo de leitura da resposta

        return factory;
    }

    @Override
    public void enableMonitoring(TSID sensorId) {
        restClient.put()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve() // Executa a requisição e obtém a resposta
                .toBodilessEntity(); // toBodilessEntity porque não precisamos do corpo da resposta
    }

    @Override
    public void disableMonitoring(TSID sensorId) {
        restClient.delete()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity(); // toBodilessEntity porque não precisamos do corpo da resposta
    }
}
