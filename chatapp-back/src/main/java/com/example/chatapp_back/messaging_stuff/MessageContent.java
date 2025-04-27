package com.example.chatapp_back.messaging_stuff;

import org.jilt.Builder;

@Builder
public record MessageContent(String text,
                             MessageType type,
                             MessageMediaContent media) {
}