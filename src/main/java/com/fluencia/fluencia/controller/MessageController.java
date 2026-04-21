package com.fluencia.fluencia.controller;

import com.fluencia.fluencia.model.Message;
import com.fluencia.fluencia.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Message> recebeMensagem(@RequestBody Message body) {
        Message message = service.save(body);
        return ResponseEntity.ok(message);
    }
}
