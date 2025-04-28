package com.example.chatapp_back.common_stuff.jpa_stuff;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.EntityListeners;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID> implements Serializable {

    @CreatedDate
    @Column(name = "created_timestamp", updatable = false, nullable = false)
    private Instant timestamp = Instant.now();

    @LastModifiedDate
    @Column(name = "updated_timestamp")
    private Instant updated_timestamp = Instant.now();


    public abstract ID getId();

    public Instant getCreatedAt() {
        return timestamp;
    }

    public void setCreatedAt(Instant createdAt) {
        this.timestamp = createdAt;
    }

    public Instant getUpdatedAt() {
        return updated_timestamp;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updated_timestamp = updatedAt;
    }

}