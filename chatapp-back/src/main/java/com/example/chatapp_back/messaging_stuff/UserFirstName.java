package com.example.chatapp_back.messaging_stuff;

public record UserFirstName(String value) {

    public UserFirstName {
        if (value == null) {
            throw new IllegalArgumentException("'value' cannot be null");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("'value' cannot exceed 255 characters");
        }
    }
}
