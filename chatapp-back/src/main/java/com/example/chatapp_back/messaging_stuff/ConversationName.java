package com.example.chatapp_back.messaging_stuff;

public record ConversationName(String name) {

    public ConversationName {
        // Validate the 'name' field manually without using custom Assert class
        
        if (name == null || name.length() < 3 || name.length() > 255) {
            throw new IllegalArgumentException("Name must be between 3 and 255 characters long");
        }
    }
}
