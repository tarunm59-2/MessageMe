package com.example.chatapp_back.readers;
import java.util.List;
import java.util.UUID;

public record ConversationViewedForNotification(UUID conversationId,
                                                List<UUID> messageIdsViewed) {
}
