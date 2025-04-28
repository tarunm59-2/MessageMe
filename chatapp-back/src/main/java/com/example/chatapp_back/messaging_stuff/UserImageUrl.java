package com.example.chatapp_back.messaging_stuff;

public record UserImageUrl(String value) {

    public UserImageUrl {
        if (value == null) {
            throw new IllegalArgumentException("'value' cannot be null");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("'value' cannot exceed 255 characters");
        }
    }
}
