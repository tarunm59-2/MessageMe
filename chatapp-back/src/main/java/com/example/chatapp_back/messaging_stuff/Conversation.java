package com.example.chatapp_back.messaging_stuff;


import org.jilt.Builder;

import java.util.Objects;
import java.util.Set;

@Builder
public class Conversation {

    private final Set<Message> messages;
    private final Set<User> members;
    private final ConversationPublicId conversationPublicId;
    private final ConversationName conversationName;
    private Long dbId;

    public Conversation(Set<Message> messages, Set<User> members, ConversationPublicId conversationPublicId, ConversationName conversationName, Long dbId) {
        this.members = Objects.requireNonNull(members, "members must not be null");
        this.conversationName = Objects.requireNonNull(conversationName, "conversationName must not be null");
        this.messages = messages;
        this.conversationPublicId = conversationPublicId;
        this.dbId = dbId;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public Set<User> getMembers() {
        return members;
    }

    public ConversationPublicId getConversationPublicId() {
        return conversationPublicId;
    }

    public ConversationName getConversationName() {
        return conversationName;
    }

    public Long getDbId() {
        return dbId;
    }
}
