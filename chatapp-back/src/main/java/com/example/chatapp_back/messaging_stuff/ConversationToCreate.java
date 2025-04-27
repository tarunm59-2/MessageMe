package com.example.chatapp_back.messaging_stuff;

import org.jilt.Builder;

import java.util.Objects;
import java.util.Set;

@Builder
public class ConversationToCreate {

    private final Set<UserPublicId> members;
    private final ConversationName name;

    public ConversationToCreate(Set<UserPublicId> members, ConversationName name) {
        this.members = Objects.requireNonNull(members, "members must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public Set<UserPublicId> getMembers() {
        return members;
    }

    public ConversationName getName() {
        return name;
    }
}