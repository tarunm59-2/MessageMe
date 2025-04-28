package com.example.chatapp_back.infra;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;
import org.jilt.Builder;

import com.example.chatapp_back.common_stuff.jpa_stuff.BaseEntity;
import com.example.chatapp_back.messaging_stuff.Conversation;
import com.example.chatapp_back.messaging_stuff.ConversationBuilder;
import com.example.chatapp_back.messaging_stuff.ConversationName;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.ConversationToCreate;

import jakarta.persistence.*;

@Entity
@Table(name = "conversation")
@Builder
public class ConversationEntity extends BaseEntity<Long>  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @UuidGenerator
    @Column(name = "public_id", nullable = false, updatable = false)
    private UUID publicId;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conversation")
    private Set<MessageEntity> messages = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_conversation",
            joinColumns = {@JoinColumn(name = "conversation_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
    )
    private Set<UserEntity> users = new HashSet<>();

    public ConversationEntity(Long id, UUID publicId, String name, Set<MessageEntity> messages, Set<UserEntity> users) {
        this.id = id;
        this.publicId = publicId;
        this.name = name;
        this.messages = messages;
        this.users = users;
    }

    public ConversationEntity() {
    }


    public static ConversationEntity from(Conversation conversation) {
        ConversationEntityBuilder conversationEntityBuilder = ConversationEntityBuilder.conversationEntity();

        if (conversation.getDbId() != null) {
            conversationEntityBuilder.id(conversation.getDbId());
        }

        if (conversation.getConversationName() != null) {
            conversationEntityBuilder.name(conversation.getConversationName().name());
        }

        if (conversation.getConversationPublicId() != null) {
            conversationEntityBuilder.publicId(conversation.getConversationPublicId().value());
        }

        if (conversation.getMessages() != null) {
            Set<MessageEntity> messages = conversation.getMessages()
                    .stream().map(MessageEntity::from).collect(Collectors.toSet());
            conversationEntityBuilder.messages(messages);
        }

        conversationEntityBuilder.users(UserEntity.from(conversation.getMembers().stream().toList()));

        return conversationEntityBuilder.build();
    }

    public static Conversation toDomain(ConversationEntity conversation) {
        ConversationBuilder conversationEntityBuilder = ConversationBuilder
                .conversation()
                .conversationPublicId(new ConversationPublicId(conversation.getPublicId()))
                .conversationName(new ConversationName(conversation.getName()))
                .members(UserEntity.toDomain(conversation.getUsers()))
                .dbId(conversation.getId());

        if (conversation.getMessages() != null) {
            conversationEntityBuilder.messages(MessageEntity.toDomain(conversation.getMessages()));
        }

        return conversationEntityBuilder.build();
    }

    public static ConversationEntity from(ConversationToCreate conversation) {
        ConversationEntityBuilder conversationEntityBuilder = ConversationEntityBuilder.conversationEntity();

        if (conversation.getName() != null) {
            conversationEntityBuilder.name(conversation.getName().name());
        }

        return conversationEntityBuilder.build();
    }

    @Override
    public Long getId() {
        return id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public String getName() {
        return name;
    }

    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationEntity that = (ConversationEntity) o;
        return Objects.equals(publicId, that.publicId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicId, name);
    }
}


