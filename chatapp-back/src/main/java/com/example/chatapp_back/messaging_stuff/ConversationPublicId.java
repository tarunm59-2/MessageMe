package com.example.chatapp_back.messaging_stuff;
import java.util.UUID;
public record ConversationPubliId(UUID ID) {

    public ConversationPubliId {
        
        if (ID == null) {
            throw new IllegalArgumentException("Name must be between 3 and 255 characters long");
        }
    }
}