package com.example.chatapp_back.messaging_stuff;
import org.springframework.util.Assert;

import java.util.UUID;

public record ConversationPublicId(UUID value) {

    public ConversationPublicId {
        Assert.notNull(value, "conversation cannot be null");
    }
}