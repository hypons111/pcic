package gov.pcc.pwc.security.user;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableSet;

/**
 * API 使用者物件。
 */
@AutoValue
public abstract class ApiAccessToken implements User {

    public abstract ImmutableSet<Role> getRoles();

    public abstract ImmutableSet<Authority> getAuthorities();

    public static Builder builder() {
        return new AutoValue_ApiAccessToken.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(String newId);

        public abstract Builder setName(String newName);

        abstract ImmutableSet.Builder<Role> rolesBuilder();

        public Builder addRole(String id, String name) {
            rolesBuilder().add(new SimpleRole(id, name));
            return this;
        }

        public Builder addRole(String id) {
            return addRole(id);
        }

        abstract ImmutableSet.Builder<Authority> authoritiesBuilder();

        public Builder addAuthority(String id, String name) {
            authoritiesBuilder().add(new SimpleAuthority(id, name));
            return this;
        }

        public Builder addAuthority(String id) {
            return addAuthority(id, id);
        }

        public abstract ApiAccessToken build();
    }
}
