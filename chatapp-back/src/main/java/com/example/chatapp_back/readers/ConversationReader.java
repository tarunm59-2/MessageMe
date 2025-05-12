package com.example.chatapp_back.readers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.chatapp_back.messaging_stuff.Conversation;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.ConversationRepository;
import com.example.chatapp_back.messaging_stuff.UserPublicId;

public class ConversationReader {

    private final ConversationRepository conversationRepository;

    public ConversationReader(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Page<Conversation> getAllByUserPublicID(UserPublicId userPublicId, Pageable pageable) {
        return conversationRepository.getConversationByUserPublicId(userPublicId, pageable);
    }

    public Optional<Conversation> getOneByPublicId(ConversationPublicId conversationPublicId) {
        return conversationRepository.getOneByPublicId(conversationPublicId);
    }

    public Optional<Conversation> getOneByPublicIdAndUserId(ConversationPublicId conversationPublicId, UserPublicId userPublicId) {
        return conversationRepository.getConversationByUsersPublicIdAndPublicId(userPublicId, conversationPublicId);
    }
}
