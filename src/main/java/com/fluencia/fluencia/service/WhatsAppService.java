package com.fluencia.fluencia.service;

import com.fluencia.fluencia.dto.ResponseDTO;
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

    public WhatsAppService(RestClient.Builder client, @Value("${evolution.instance-name}") String instanceName, @Value("${AUTHENTICATION_API_KEY}") String apiKey) {
        this.client = client
                .baseUrl("http://localhost:5050/message/sendText/" + instanceName)
                .build();
        this.apiKey = apiKey;
    }

    public void enviaMensagem(String number, String text) {
        ResponseDTO dto = new ResponseDTO(number, text);
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
