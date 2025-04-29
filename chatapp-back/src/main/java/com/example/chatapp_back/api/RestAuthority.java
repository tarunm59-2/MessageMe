package com.example.chatapp_back.api;

import org.jilt.Builder;

import java.util.Set;
import java.util.stream.Collectors;
import com.example.chatapp_back.messaging_stuff.*;
@Builder
public record RestAuthority(String name) {

    public static Set<RestAuthority> fromSet(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> RestAuthorityBuilder.restAuthority().name(authority.getName().name()).build())
                .collect(Collectors.toSet());
    }

}
