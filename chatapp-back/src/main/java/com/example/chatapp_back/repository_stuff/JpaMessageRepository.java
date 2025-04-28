package com.example.chatapp_back.repository_stuff;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatapp_back.infra.MessageEntity;

public interface JpaMessageRepository extends JpaRepository<MessageEntity, Long> {

}
