package com.example.chatapp_back.messaging_stuff;


import java.util.List;
import java.util.Objects;

public record Messages(List<Messages> messages) {
    public Messages {
        if (messages == null) {
            throw new IllegalArgumentException("'messages' must not be null");
        }
        for (Messages message : messages) {
            if (message == null) {
                throw new IllegalArgumentException("'messages' must not contain null elements");
            }
        }
    }
}
