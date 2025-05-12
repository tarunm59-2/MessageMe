package com.example.chatapp_back.readers;

import java.util.List;
import java.util.Optional;

import com.example.chatapp_back.common_stuff.authentication.UserReader;
import com.example.chatapp_back.common_stuff.utilities.Current_State;
import com.example.chatapp_back.messaging_stuff.ConversationRepository;
import com.example.chatapp_back.messaging_stuff.ConversationToCreate;
import com.example.chatapp_back.messaging_stuff.*;
public class ConversationCreator {

    private final ConversationRepository conversationRepository;
    private final UserReader userReader;

    public ConversationCreator(ConversationRepository conversationRepository, UserReader userReader) {
        this.conversationRepository = conversationRepository;
        this.userReader = userReader;
    }


    public Current_State<Conversation, String> create(ConversationToCreate newConversation, User authenticatedUser) {
        newConversation.getMembers().add(authenticatedUser.getUserPublicId());
        List<User> members = userReader.getUsersByPublicId(newConversation.getMembers());
        List<UserPublicId> membersUuids = members.stream().map(User::getUserPublicId).toList();
        Optional<Conversation> conversationAlreadyPresent = conversationRepository.getConversationByUserPublicIds(membersUuids);
        Current_State<Conversation, String> stateResult;
        if (conversationAlreadyPresent.isEmpty()) {
            Conversation newConversationSaved = conversationRepository.save(newConversation, members);
            stateResult = Current_State.<Conversation, String>builder().SuccessCase(newConversationSaved);
        } else {
            stateResult = Current_State.<Conversation, String>builder().ErrorCase("This conversation already exists");
        }
        return stateResult;
    }
}
