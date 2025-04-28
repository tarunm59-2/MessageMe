package com.example.chatapp_back.repository_stuff;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.chatapp_back.messaging_stuff.Conversation;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.ConversationRepository;
import com.example.chatapp_back.messaging_stuff.ConversationToCreate;
import com.example.chatapp_back.messaging_stuff.User;
import com.example.chatapp_back.messaging_stuff.UserPublicId;

public class SpringDataConversationRepository implements ConversationRepository{

    @Override
    public Conversation save(ConversationToCreate conversation, List<User> members) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Conversation> get(ConversationPublicId conversationPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Page<Conversation> getConversationByUserPublicId(UserPublicId publicId, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConversationByUserPublicId'");
    }

    @Override
    public int deleteByPublicId(UserPublicId userPublicId, ConversationPublicId conversationPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByPublicId'");
    }

    @Override
    public Optional<Conversation> getConversationByUsersPublicIdAndPublicId(UserPublicId userPublicId,
            ConversationPublicId conversationPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConversationByUsersPublicIdAndPublicId'");
    }

    @Override
    public Optional<Conversation> getConversationByUserPublicIds(List<UserPublicId> publicIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConversationByUserPublicIds'");
    }

    @Override
    public Optional<Conversation> getOneByPublicId(ConversationPublicId conversationPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOneByPublicId'");
    }
    

}
