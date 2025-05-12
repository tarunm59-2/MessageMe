package com.example.chatapp_back.readers;


import org.springframework.stereotype.Service;

import com.example.chatapp_back.common_stuff.utilities.Current_State;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.Message;
import com.example.chatapp_back.messaging_stuff.UserPublicId;

import java.util.List;

@Service
public class SpringEventMessageChangeNotifier implements MessageChangeNotifier {

    @Override
    public Current_State<Void, String> send(Message message, List<UserPublicId> userToNotify) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }

    @Override
    public Current_State<Void, String> delete(ConversationPublicId conversationPublicId,
            List<UserPublicId> userToNotify) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Current_State<Void, String> view(ConversationViewedForNotification conversationViewedForNotification,
            List<UserPublicId> usersToNotify) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'view'");
    }

    
}
