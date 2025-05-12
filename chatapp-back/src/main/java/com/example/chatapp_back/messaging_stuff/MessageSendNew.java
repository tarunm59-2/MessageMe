package com.example.chatapp_back.messaging_stuff;

import org.jilt.Builder;

@Builder
public record MessageSendNew(MessageContent messageContent,
                             ConversationPublicId conversationPublicId) {
}