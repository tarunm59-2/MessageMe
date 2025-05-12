package com.example.chatapp_back.readers;

import java.util.List;

import com.example.chatapp_back.common_stuff.authentication.UserReader;
import com.example.chatapp_back.common_stuff.utilities.Current_State;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.Message;
import com.example.chatapp_back.messaging_stuff.MessageRepository;
import com.example.chatapp_back.messaging_stuff.MessageSendState;
import com.example.chatapp_back.messaging_stuff.User;
import com.example.chatapp_back.messaging_stuff.UserPublicId;

public class ConversationViewed {

    private final MessageRepository messageRepository;
    private final MessageChangeNotifier messageChangeNotifier;
    private final UserReader userReader;

    public ConversationViewed(MessageRepository messageRepository, MessageChangeNotifier messageChangeNotifier, UserReader userReader) {
        this.messageRepository = messageRepository;
        this.messageChangeNotifier = messageChangeNotifier;
        this.userReader = userReader;
    }

    public Current_State<Integer, String> markAsRead(ConversationPublicId conversationPublicId, UserPublicId connectedUserPublicId) {
        List<Message> messageToUpdateSendState = messageRepository.findMessageToUpdateSendState(conversationPublicId, connectedUserPublicId);
        int nbUpdatedMessages = messageRepository.updateMessageSendState(conversationPublicId, connectedUserPublicId, MessageSendState.READ);
        List<UserPublicId> usersToNotify = userReader.findUsersToNotify(conversationPublicId, connectedUserPublicId)
                .stream().map(User::getUserPublicId).toList();
        ConversationViewedForNotification conversationViewedForNotification = new ConversationViewedForNotification(conversationPublicId.value(),
                messageToUpdateSendState.stream().map(message -> message.getPublicId().value()).toList());
        messageChangeNotifier.view(conversationViewedForNotification, usersToNotify);

        if (nbUpdatedMessages > 0) {
            return Current_State.<Integer, String>builder().SuccessCase(nbUpdatedMessages);
        } else {
            return Current_State.<Integer, String>builder().SuccessCase();
        }
    }
}