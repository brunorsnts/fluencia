package com.fluencia.fluencia.service;

import com.fluencia.fluencia.model.Message;
import com.fluencia.fluencia.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Message save(Message message) {
        return repository.save(message);
    }
}
