package com.example.chatapp_back.messaging_stuff;

import java.util.List;
import java.util.Objects;

public record Conversations(List<Conversation> conversations) {

    public Conversations {
        Objects.requireNonNull(conversations, "conversations must not be null");
        if (conversations.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("conversations must not contain null elements");
        }
    }
}
