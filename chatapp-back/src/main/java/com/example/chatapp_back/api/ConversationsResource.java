package com.example.chatapp_back.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatapp_back.common_stuff.utilities.Current_State;
import com.example.chatapp_back.common_stuff.utilities.Notification;
import com.example.chatapp_back.messaging_stuff.Conversation;
import com.example.chatapp_back.messaging_stuff.ConversationPublicId;
import com.example.chatapp_back.messaging_stuff.ConversationToCreate;
import com.example.chatapp_back.messaging_stuff.ConversationsApplicationService;

@RestController
@RequestMapping("/api/conversations")
public class ConversationsResource {

    private final ConversationsApplicationService conversationsApplicationService;

    public ConversationsResource(ConversationsApplicationService conversationsApplicationService) {
        this.conversationsApplicationService = conversationsApplicationService;
    }

    @GetMapping
    ResponseEntity<List<RestConversation>> getAll(Pageable pageable) {
        List<RestConversation> restConversations = conversationsApplicationService.getAllByConnectedUser(pageable)
                .stream().map(RestConversation::from).toList();
        return ResponseEntity.ok(restConversations);
    }

    @PostMapping
    ResponseEntity<RestConversation> create(@RequestBody
                                            RestConversationToCreate restConversationToCreate) {
        ConversationToCreate newConversation = RestConversationToCreate.toDomain(restConversationToCreate);
        Current_State<Conversation, String> conversationState = conversationsApplicationService.create(newConversation);
        if (conversationState.get_curr_status().equals(Notification.OK)) {
            RestConversation restConversations = RestConversation.from(conversationState.getValue());
            return ResponseEntity.ok(restConversations);
        } else {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Not allowed to create conversation");
            return ResponseEntity.of(problemDetail).build();
        }
    }

    @DeleteMapping
    ResponseEntity<UUID> delete(@RequestParam UUID publicId) {
        Current_State<ConversationPublicId, String> restConversation = conversationsApplicationService.delete(new ConversationPublicId(publicId));
        if (restConversation.get_curr_status().equals(Notification.OK)) {
            return ResponseEntity.ok(publicId);
        } else {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Not allowed to delete conversation");
            return ResponseEntity.of(problemDetail).build();
        }
    }

    @GetMapping("/get-one-by-public-id")
    ResponseEntity<RestConversation> getOneByPublicId(@RequestParam UUID conversationId) {
        Optional<RestConversation> restConversation = conversationsApplicationService.getOneByConversationId(new ConversationPublicId(conversationId))
                .map(RestConversation::from);
        if (restConversation.isPresent()) {
            return ResponseEntity.ok(restConversation.get());
        } else {
            ProblemDetail problemDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Not able to find this conversation");
            return ResponseEntity.of(problemDetail).build();
        }
    }

    @PostMapping("/mark-as-read")
    ResponseEntity<Integer> markConversationAsRead(@RequestParam UUID conversationId) {
        Current_State<Integer, String> readUpdateState = conversationsApplicationService.markConversationAsRead(
                new ConversationPublicId(conversationId));
        return ResponseEntity.ok(readUpdateState.getValue());
    }
}