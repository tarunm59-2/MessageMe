package com.example.chatapp_back.messaging_stuff;


import java.util.List;

public interface MessageRepository {

    Message save(Message message, User sender, Conversation conversation);

    int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId, MessageSendState state);

    List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId);

}