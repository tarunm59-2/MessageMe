package com.example.chatapp_back.infra;


import com.example.chatapp_back.messaging_stuff.Authority;
import com.example.chatapp_back.messaging_stuff.AuthorityBuilder;
import com.example.chatapp_back.messaging_stuff.AuthorityName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.jilt.Builder;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "authority")
@Builder
public class AuthorityEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 50)
    private String name;

    public AuthorityEntity() {
        // JPA requires a no-arg constructor
    }

    public AuthorityEntity(String name) {
        if (name == null) {
            throw new IllegalArgumentException("'name' cannot be null");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("'name' cannot be longer than 50 characters");
        }
        this.name = name;
    }

    public static Set<AuthorityEntity> from(Set<Authority> authorities) {
        return authorities
                .stream()
                .map(authority ->
                        com.example.chatapp_back.infra.AuthorityEntityBuilder.authorityEntity()
                                .name(authority.getName().name())
                                .build())
                .collect(Collectors.toSet());
    }

    public static Set<Authority> toDomain(Set<AuthorityEntity> authorityEntities) {
        return authorityEntities.stream()
                .map(authority -> AuthorityBuilder.authority()
                        .name(new AuthorityName(authority.name))
                        .build())
                .collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("'name' cannot be null");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("'name' cannot be longer than 50 characters");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityEntity that = (AuthorityEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
