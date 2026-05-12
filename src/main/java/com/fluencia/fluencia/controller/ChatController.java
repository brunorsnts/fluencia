package com.fluencia.fluencia.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fluencia.fluencia.service.Assistant;
import com.fluencia.fluencia.service.WhatsAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class ChatController {
    private final Assistant assistant;
    private final WhatsAppService whatsAppService;

    public ChatController(Assistant assistant,
                          WhatsAppService whatsAppService) {
        this.assistant = assistant;
        this.whatsAppService = whatsAppService;
    }

    @PostMapping
    public ResponseEntity<Void> recebeMensagem(@RequestBody JsonNode json) {
        if (json.at("/data/key/fromMe").asBoolean()) {
            return ResponseEntity.ok().build();
        }
        String mensagem = json.at("/data/message/conversation").asText();
        String number = json.at("/data/key/remoteJid").asText();
        String respostaIA = assistant.chat(mensagem, number);
        whatsAppService.enviaMensagem(number, respostaIA);
        return ResponseEntity.ok().build();
    }
}
