package com.example.chatapp_back.repository_stuff;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.chatapp_back.messaging_stuff.Conversation;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.Message;
import com.example.chatapp_back.messaging_stuff.MessageRepository;
import com.example.chatapp_back.messaging_stuff.MessageSendState;
import com.example.chatapp_back.messaging_stuff.User;
import com.example.chatapp_back.messaging_stuff.UserPublicId;

@Repository

public class SpringDataMessageRepository implements MessageRepository {

    @Override
    public Message save(Message message, User sender, Conversation conversation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int updateMessageSendState(ConversationPublicId conversationPublicId, UserPublicId userPublicId,
            MessageSendState state) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessageSendState'");
    }

    @Override
    public List<Message> findMessageToUpdateSendState(ConversationPublicId conversationPublicId,
            UserPublicId userPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMessageToUpdateSendState'");
    }
    
}
