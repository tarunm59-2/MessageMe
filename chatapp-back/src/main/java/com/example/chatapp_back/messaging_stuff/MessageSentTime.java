package com.example.chatapp_back.messaging_stuff;

import java.time.Instant;

public record MessageSentTime(Instant date) {
    public MessageSentTime {
        if (date == null  ) {
            throw new IllegalArgumentException("Name must be between 3 and 255 characters long");
        }
    }
}