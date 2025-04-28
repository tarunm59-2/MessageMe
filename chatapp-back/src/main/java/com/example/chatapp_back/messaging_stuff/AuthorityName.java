package com.example.chatapp_back.messaging_stuff;

public record AuthorityName(String name) {

    public AuthorityName {
        if (name == null) {
            throw new IllegalArgumentException("'name' cannot be null");
        }
    }
}
