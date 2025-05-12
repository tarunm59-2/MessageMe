package com.example.chatapp_back.readers;

import java.util.List;


import com.example.chatapp_back.common_stuff.utilities.Current_State;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.Message;
import com.example.chatapp_back.messaging_stuff.UserPublicId;


public interface MessageChangeNotifier {

    Current_State<Void, String> send(Message message, List<UserPublicId> userToNotify);

    Current_State<Void, String> delete(ConversationPublicId conversationPublicId, List<UserPublicId> userToNotify);

    Current_State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification, List<UserPublicId> usersToNotify);
}