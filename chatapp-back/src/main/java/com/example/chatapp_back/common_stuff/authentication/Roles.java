package com.example.chatapp_back.common_stuff.authentication;

import java.util.Collections;
import java.util.Set;

public record Roles(Set<Role> roles) {

  public static final Roles EMPTY = new Roles(Collections.emptySet());

  public Roles(Set<Role> roles) {
    this.roles = Collections.unmodifiableSet(roles);  // Make the set immutable
  }

  public boolean hasRole() {
    return !roles.isEmpty();  // Checks if the set is not empty
  }

  public boolean hasRole(Role role) {
    if (role == null) {
      throw new IllegalArgumentException("role cannot be null");  // Handle null role input
    }
    return roles.contains(role);  // Check if the set contains the specified role
  }

  public Set<Role> get() {
    return roles;  // Simply return the roles set
  }
}
