package com.example.chatapp_back.repository_stuff;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.User;
import com.example.chatapp_back.messaging_stuff.UserEmail;
import com.example.chatapp_back.messaging_stuff.UserPublicId;
import com.example.chatapp_back.messaging_stuff.UserRepository;

public class SpringDataUserRepository implements UserRepository{

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<User> get(UserPublicId userPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Optional<User> getOneByEmail(UserEmail userEmail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOneByEmail'");
    }

    @Override
    public List<User> getByPublicIds(Set<UserPublicId> userPublicIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByPublicIds'");
    }

    @Override
    public Page<User> search(Pageable pageable, String query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public int updateLastSeenByPublicId(UserPublicId userPublicId, Instant lastSeen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLastSeenByPublicId'");
    }

    @Override
    public List<User> getRecipientByConversationIdExcludingReader(ConversationPublicId conversationPublicId,
            UserPublicId readerPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecipientByConversationIdExcludingReader'");
    }

    @Override
    public Optional<User> getOneByPublicId(UserPublicId userPublicId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOneByPublicId'");
    }

}
