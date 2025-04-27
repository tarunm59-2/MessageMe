package com.example.chatapp_back.messaging_stuff;

import java.util.Objects;
import java.util.UUID;

public record UserPublicId(UUID value) {
    public UserPublicId {
        if (value == null) {
            throw new IllegalArgumentException("'value' cannot be null");
        }
    }
}
