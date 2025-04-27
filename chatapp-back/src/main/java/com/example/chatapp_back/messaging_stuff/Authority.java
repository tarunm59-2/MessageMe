package fr.codecake.whatsappclone.messaging.domain.user.aggregate;

import org.jilt.Builder;

@Builder
public class Authority {

    private AuthorityName name;

    public Authority(AuthorityName name) {
        if (name == null) {
            throw new IllegalArgumentException("'name' cannot be null");
        }
        this.name = name;
    }

    public AuthorityName getName() {
        return name;
    }
}
