package com.fluencia.fluencia.repository;

import com.fluencia.fluencia.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
