package com.example.chatapp_back.common_stuff.authentication;

import java.util.HashMap;
import java.util.Map;

public enum Role {
  ADMIN,
  USER,
  ANONYMOUS,
  UNKNOWN;

  private static final String PREFIX = "ROLE_";
  private static final Map<String, Role> ROLES = buildRoles();

  private static Map<String, Role> buildRoles() {
    Map<String, Role> rolesMap = new HashMap<>();
    rolesMap.put(ADMIN.key(), ADMIN);
    rolesMap.put(USER.key(), USER);
    rolesMap.put(ANONYMOUS.key(), ANONYMOUS);
    rolesMap.put(UNKNOWN.key(), UNKNOWN);
    return rolesMap;
  }

  // Get the key for each role (e.g., "ROLE_ADMIN")
  public String key() {
    return PREFIX + name();
  }

  // Convert a string to the corresponding Role, or return UNKNOWN if not found
  public static Role from(String role) {
    if (role == null || role.isBlank()) {
      throw new IllegalArgumentException("Role string must not be null or blank");
    }
    return ROLES.getOrDefault(role, UNKNOWN);
  }
}
