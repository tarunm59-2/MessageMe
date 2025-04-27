
import org.jilt.Builder;

import java.util.Objects;

@Builder
public class Message {

    private final MessageSentTime sentTime;
    private final MessageContent content;
    private final MessageSendState sendState;
    private final MessagePublicId publicId;
    private final UserPublicId sender;
    private final ConversationPublicId conversationId;

    public Message(MessageSentTime sentTime, MessageContent content,
                   MessageSendState sendState, MessagePublicId publicId,
                   UserPublicId sender, ConversationPublicId conversationId) {

        this.sentTime = Objects.requireNonNull(sentTime, "sentTime must not be null");
        this.content = Objects.requireNonNull(content, "content must not be null");
        this.sendState = Objects.requireNonNull(sendState, "sendState must not be null");
        this.publicId = Objects.requireNonNull(publicId, "publicId must not be null");
        this.sender = Objects.requireNonNull(sender, "sender must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
    }

    public MessageSentTime getSentTime() {
        return sentTime;
    }

    public MessageContent getContent() {
        return content;
    }

    public MessageSendState getSendState() {
        return sendState;
    }

    public MessagePublicId getPublicId() {
        return publicId;
    }

    public UserPublicId getSender() {
        return sender;
    }

    public ConversationPublicId getConversationId() {
        return conversationId;
    }
}
