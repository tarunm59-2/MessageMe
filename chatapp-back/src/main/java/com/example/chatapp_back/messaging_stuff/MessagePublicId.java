package com.example.chatapp_back.messaging_stuff;

import java.util.UUID;

public record MessagePublicId(UUID value) {

    public MessagePublicId {
        if (value == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
    }
}
