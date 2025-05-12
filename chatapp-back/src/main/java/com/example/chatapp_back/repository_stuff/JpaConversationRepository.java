package com.example.chatapp_back.repository_stuff;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.chatapp_back.infra.ConversationEntity;

public interface JpaConversationRepository extends JpaRepository<ConversationEntity, Long> {

    Page<ConversationEntity> findAllByUsersPublicId(UUID publicId, Pageable pageable);

    int deleteByUsersPublicIdAndPublicId(UUID userPublicId, UUID conversationPublicId);

    Optional<ConversationEntity> findOneByUsersPublicIdAndPublicId(UUID userPublicId, UUID conversationPublicId);

    @Query("SELECT conversation FROM ConversationEntity conversation " +
            "JOIN conversation.users users " +
            "WHERE users.publicId IN :userPublicIds " +
            "GROUP BY conversation.id " +
            "HAVING COUNT(users.id) = :userCount")
    Optional<ConversationEntity> findOneByUsersPublicIdIn(List<UUID> userPublicIds, int userCount);

    Optional<ConversationEntity> findOneByPublicId(UUID publicId);
}
