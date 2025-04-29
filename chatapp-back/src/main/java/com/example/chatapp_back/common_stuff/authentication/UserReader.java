package com.example.chatapp_back.common_stuff.authentication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import  com.example.chatapp_back.messaging_stuff.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserReader {

    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByEmail(UserEmail email) {
        return userRepository.getOneByEmail(email);
    }

    public List<User> getUsersByPublicId(Set<UserPublicId> publicIds) {
        return userRepository.getByPublicIds(publicIds);
    }

    public Page<User> search(Pageable pageable, String query) {
        return userRepository.search(pageable, query);
    }

    public Optional<User> getByPublicId(UserPublicId publicId) {
        return userRepository.getOneByPublicId(publicId);
    }

    public List<User> findUsersToNotify(ConversationPublicId conversationPublicId, UserPublicId readerPublicId) {
        return userRepository.getRecipientByConversationIdExcludingReader(conversationPublicId, readerPublicId);
    }
}