package com.example.chatapp_back.api;

import org.jilt.Builder;
import com.example.chatapp_back.messaging_stuff.*;
import java.util.Set;
import java.util.UUID;

@Builder
public record RestUser(UUID publicId,
                       String firstName,
                       String lastName,
                       String email,
                       String imageUrl,
                       Set<RestAuthority> authorities) {

    static RestUser from(User user) {
        RestUserBuilder restUserBuilder = RestUserBuilder.restUser();

        if(user.getImageUrl() != null) {
            restUserBuilder.imageUrl(user.getImageUrl().value());
        }

        return restUserBuilder
                .email(user.getEmail().value())
                .firstName(user.getFirstname().value())
                .lastName(user.getLastName().value())
                .publicId(user.getUserPublicId().value())
                .authorities(RestAuthority.fromSet(user.getAuthorities()))
                .build();
    }
}