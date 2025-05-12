package com.example.chatapp_back.readers;


import java.util.Optional;

import com.example.chatapp_back.common_stuff.utilities.Current_State;
import com.example.chatapp_back.messaging_stuff.Conversation;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.ConversationRepository;
import com.example.chatapp_back.messaging_stuff.User;

public class ConversationDeleter {

    private final ConversationRepository conversationRepository;
    private final MessageChangeNotifier messageChangeNotifier;

    public ConversationDeleter(ConversationRepository conversationRepository, MessageChangeNotifier messageChangeNotifier) {
        this.conversationRepository = conversationRepository;
        this.messageChangeNotifier = messageChangeNotifier;
    }

    public Current_State<ConversationPublicId, String> deleteById(ConversationPublicId conversationId, User connectedUser) {
        Current_State<ConversationPublicId, String> stateResult;

        Optional<Conversation> conversationToDeleteOpt = this.conversationRepository.getConversationByUsersPublicIdAndPublicId(connectedUser.getUserPublicId(), conversationId);
        if (conversationToDeleteOpt.isPresent()) {
            this.conversationRepository.deleteByPublicId(connectedUser.getUserPublicId(), conversationId);
            messageChangeNotifier.delete(conversationId, conversationToDeleteOpt.get()
                    .getMembers().stream().map(User::getUserPublicId).toList());
            stateResult = Current_State.<ConversationPublicId, String>builder().SuccessCase(conversationId);
        } else {
            stateResult = Current_State.<ConversationPublicId, String>builder().ErrorCase("This conversation doesn't belong to this user or doesn't exist");
        }
        return stateResult;
    }
}