package com.fluencia.fluencia.service;

import com.fluencia.fluencia.dto.RequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WhatsAppService {

    private final Logger log = LoggerFactory.getLogger(WhatsAppService.class);

    private final RestClient client;
    private final String apiKey;

    public WhatsAppService(RestClient.Builder client,
                           @Value("${evolution.instance-name}") String instanceName,
                           @Value("${AUTHENTICATION_API_KEY}") String apiKey,
                           @Value("${url.send-text}") String url) {
        this.client = client
                .baseUrl(url + instanceName)
                .build();
        this.apiKey = apiKey;
    }

    public void enviaMensagem(String number, String text) {
        RequestDTO dto = new RequestDTO(number, text);
        client
                .post()
                .header("apiKey", apiKey)
                .body(dto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    log.error("Erro: {}", response.getStatusCode());
                })
                .toBodilessEntity();
    }
}
