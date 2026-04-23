package com.fluencia.fluencia.controller;

import com.fluencia.fluencia.service.AIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final AIService service;

    public WebhookController(AIService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> recebeMensagem(@RequestBody JsonNode json) {
        String mensagem = json.at("/data/message/conversation").asText();
        String resposta = service.processaMensagem(mensagem);
        log.info("resposta: {}", resposta);
        return ResponseEntity.ok().build();
    }
}
