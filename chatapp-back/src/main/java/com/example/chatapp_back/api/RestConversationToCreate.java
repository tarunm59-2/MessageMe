package com.example.chatapp_back.api;

import org.jilt.Builder;

import com.example.chatapp_back.messaging_stuff.ConversationName;
import com.example.chatapp_back.messaging_stuff.ConversationToCreate;
import com.example.chatapp_back.messaging_stuff.ConversationToCreateBuilder;
import com.example.chatapp_back.messaging_stuff.UserPublicId;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record RestConversationToCreate(Set<UUID> members, String name) {

    public static ConversationToCreate toDomain(RestConversationToCreate restConversationToCreate) {
        RestConversationToCreateBuilder restConversationToCreateBuilder = RestConversationToCreateBuilder.restConversationToCreate();

        Set<UserPublicId> userUUIDs = restConversationToCreate.members
                .stream()
                .map(UserPublicId::new)
                .collect(Collectors.toSet());

        return ConversationToCreateBuilder.conversationToCreate()
                .name(new ConversationName(restConversationToCreate.name()))
                .members(userUUIDs)
                .build();
    }
}