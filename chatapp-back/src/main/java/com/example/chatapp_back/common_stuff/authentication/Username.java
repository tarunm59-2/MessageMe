package com.example.chatapp_back.common_stuff.authentication;


import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public record Username(String username) {

  // Constructor that validates the username
  public Username {
    if (username == null || username.isBlank()) {
      throw new IllegalArgumentException("Username cannot be blank");
    }
    if (username.length() > 100) {
      throw new IllegalArgumentException("Username cannot exceed 100 characters");
    }
  }

  // Getter for username
  public String get() {
    return username;
  }

  // Static method to create an Optional<Username> from a String
  public static Optional<Username> of(String username) {
    if (StringUtils.isNotBlank(username)) {
      return Optional.of(new Username(username));
    } else {
      return Optional.empty();
    }
  }
}
