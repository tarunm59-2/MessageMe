package com.example.chatapp_back.messaging_stuff;
import org.jilt.Builder;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
public class User {

    private UserLastName lastName;
    private UserFirstName firstname;
    private UserEmail email;
    private UserPublicId userPublicId;
    private UserImageUrl imageUrl;
    private Instant lastModifiedDate;
    private Instant createdDate;
    private Instant lastSeen;
    private Set<Authority> authorities;
    private Long dbId;

    public User(UserLastName lastName, UserFirstName firstname, UserEmail email,
                UserPublicId userPublicId, UserImageUrl imageUrl, Instant lastModifiedDate,
                Instant createdDate, Instant lastSeen, Set<Authority> authorities, Long dbId) {
        assertMandatoryFields(lastName, firstname, email, authorities);
        this.lastName = lastName;
        this.firstname = firstname;
        this.email = email;
        this.userPublicId = userPublicId;
        this.imageUrl = imageUrl;
        this.lastModifiedDate = lastModifiedDate;
        this.createdDate = createdDate;
        this.lastSeen = lastSeen;
        this.authorities = authorities;
        this.dbId = dbId;
    }

    private void assertMandatoryFields(UserLastName lastName,
                                       UserFirstName firstname,
                                       UserEmail email,
                                       Set<Authority> authorities) {
        if (lastName == null) {
            throw new IllegalArgumentException("'lastName' cannot be null");
        }
        if (firstname == null) {
            throw new IllegalArgumentException("'firstname' cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("'email' cannot be null");
        }
        if (authorities == null) {
            throw new IllegalArgumentException("'authorities' cannot be null");
        }
    }

    public void updateFromUser(User user) {
        this.email = user.email;
        this.lastName = user.lastName;
        this.firstname = user.firstname;
        this.imageUrl = user.imageUrl;
    }

    public static User fromTokenAttributes(Map<String, Object> attributes, List<String> rolesFromAccessToken) {
        UserBuilder userBuilder = UserBuilder.user();

        String sub = String.valueOf(attributes.get("sub"));

        String username = null;

        if (attributes.containsKey("preferred_username")) {
            username = attributes.get("preferred_username").toString().toLowerCase();
        }

        if (attributes.containsKey("given_name")) {
            userBuilder.firstname(new UserFirstName(attributes.get("given_name").toString()));
        } else if (attributes.containsKey("nickname")) {
            userBuilder.firstname(new UserFirstName(attributes.get("nickname").toString()));
        }

        if (attributes.containsKey("family_name")) {
            userBuilder.lastName(new UserLastName(attributes.get("family_name").toString()));
        }

        if (attributes.containsKey("email")) {
            userBuilder.email(new UserEmail(attributes.get("email").toString()));
        } else if (sub.contains("|") && (username != null && username.contains("@"))) {
            userBuilder.email(new UserEmail(username));
        } else {
            userBuilder.email(new UserEmail(sub));
        }

        if (attributes.containsKey("image_url")) {
            userBuilder.imageUrl(new UserImageUrl(attributes.get("image_url").toString()));
        }

        Set<Authority> authorities = rolesFromAccessToken
                .stream()
                .map(authority -> AuthorityBuilder.authority().name(new AuthorityName(authority)).build())
                .collect(Collectors.toSet());

        userBuilder.authorities(authorities);

        return userBuilder.build();
    }

    public void initFieldForSignup() {
        this.lastSeen = Instant.now();
    }

    public UserLastName getLastName() {
        return lastName;
    }

    public UserFirstName getFirstname() {
        return firstname;
    }

    public UserEmail getEmail() {
        return email;
    }

    public UserPublicId getUserPublicId() {
        return userPublicId;
    }

    public UserImageUrl getImageUrl() {
        return imageUrl;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getLastSeen() {
        return lastSeen;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public Long getDbId() {
        return dbId;
    }
}
