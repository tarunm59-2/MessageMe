package com.example.chatapp_back.common_stuff.authentication;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for accessing authenticated user details.
 */
public final class AuthenticatedUser {

    private static final String USERNAME_CLAIM = "email";

    private AuthenticatedUser() {
        // prevent instantiation
    }

    public static Username username() {
        return findUsername()
                .orElseThrow(() -> new IllegalStateException("No authenticated user found"));
    }

    public static Optional<Username> findUsername() {
        return getAuthentication()
                .map(AuthenticatedUser::extractPrincipal)
                .flatMap(Username::of);
    }

    public static Roles roles() {
        return getAuthentication()
                .map(AuthenticatedUser::mapAuthoritiesToRoles)
                .orElse(Roles.EMPTY);
    }

    public static Map<String, Object> attributes() {
        Authentication auth = getAuthentication()
                .orElseThrow(() -> new IllegalStateException("No authenticated user available"));

        if (auth instanceof JwtAuthenticationToken jwtToken) {
            return jwtToken.getTokenAttributes();
        }
        throw new IllegalArgumentException("Unsupported authentication type for attributes");
    }

    public static List<String> extractRolesFromToken(Jwt jwt) {
        Map<String, List<String>> realmAccess = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
        List<String> roles = realmAccess.getOrDefault("roles", List.of());
        List<String> filteredRoles = new ArrayList<>();

        for (String role : roles) {
        if (role.startsWith("ROLE_")) {
            filteredRoles.add(role);
        }
        }

        return filteredRoles;
    }


    private static Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    private static String extractPrincipal(Authentication auth) {
        Object principal = auth.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        if (auth instanceof JwtAuthenticationToken jwtToken) {
            return (String) jwtToken.getToken().getClaims().get(USERNAME_CLAIM);
        }
        if (principal instanceof DefaultOidcUser oidcUser) {
            return (String) oidcUser.getAttributes().get(USERNAME_CLAIM);
        }
        if (principal instanceof String strPrincipal) {
            return strPrincipal;
        }

        throw new IllegalArgumentException("Unknown authentication principal type");
    }

    private static Roles mapAuthoritiesToRoles(Authentication auth) {
        Set<Role> roles = new HashSet<>();

        for (GrantedAuthority authority : auth.getAuthorities()) {
            String authorityName = authority.getAuthority();
            Role role = Role.from(authorityName);
            roles.add(role);
        }

        return new Roles(roles);
    }

}
