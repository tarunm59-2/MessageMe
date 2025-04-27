
public record UserFirstname(String value) {

    public UserFirstname {
        if (value == null) {
            throw new IllegalArgumentException("'value' cannot be null");
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("'value' cannot exceed 255 characters");
        }
    }
}
