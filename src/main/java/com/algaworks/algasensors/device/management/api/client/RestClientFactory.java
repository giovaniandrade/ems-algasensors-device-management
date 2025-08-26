package com.algaworks.algasensors.device.management.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RestClientFactory {

    private final RestClient.Builder restClientBuilder;

    public RestClient temperatureMonitoringRestClient() {
        // Assim não funcionaria porque não carregas os módulos do Jackson
        // configurados em: TSIDJacksonConfig
        // return RestClient.create("http://localhost:8082");
        return restClientBuilder
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

}
