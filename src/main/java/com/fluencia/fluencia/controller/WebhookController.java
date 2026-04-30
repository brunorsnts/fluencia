package com.fluencia.fluencia.controller;

import com.fluencia.fluencia.service.AIService;
import com.fluencia.fluencia.service.WhatsAppService;
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

    private final AIService aiService;
    private final WhatsAppService whatsAppService;

    public WebhookController(AIService aiService, WhatsAppService whatsAppService) {
        this.aiService = aiService;
        this.whatsAppService = whatsAppService;
    }

    @PostMapping
    public ResponseEntity<Void> recebeMensagem(@RequestBody JsonNode json) {
        String mensagem = json.at("/data/message/conversation").asText();
        String number = json.at("/data/key/remoteJid").asText();
        String respostaIA = aiService.processaMensagem(mensagem);
        whatsAppService.enviaMensagem(number, respostaIA);
        log.info("resposta: {}", respostaIA);
        return ResponseEntity.ok().build();
    }
}
