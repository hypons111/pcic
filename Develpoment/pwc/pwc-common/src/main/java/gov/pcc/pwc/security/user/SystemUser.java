package gov.pcc.pwc.security.user;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableSet;

/**
 * 使用者物件。
 */
@AutoValue
public abstract class SystemUser implements User {

    /**
     * 取得使用者所屬組織單位。
     * <p>
     * 依據使用者級別不同，可能是科、室、課、股。
     *
     * @return 組織單位。
     */
    public abstract Unit getUnit();

    public abstract ImmutableSet<Role> getRoles();

    public abstract ImmutableSet<Authority> getAuthorities();

    /**
     * 取得使用者身分證字號。
     *
     * @return 身分證字號。
     */
    public abstract String getIdn();

    /**
     * 取得使用者電子郵件信箱位址。
     *
     * @return 電子郵件信箱位址。
     */
    public abstract String getEmail();

    public abstract String getUserType();

    public abstract String getPccOrgId();

    public abstract String getCompIdno();

    public abstract String getIp();

    public static Builder builder() {
        return new AutoValue_SystemUser.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setId(String newId);

        public abstract Builder setName(String newName);

        public abstract Builder setUnit(Unit newUnit);

        abstract ImmutableSet.Builder<Role> rolesBuilder();

        public Builder addRole(String id, String name) {
            rolesBuilder().add(new SimpleRole(id, name));
            return this;
        }

        public Builder addRole(String id) {
            return addRole(id, id);
        }

        abstract ImmutableSet.Builder<Authority> authoritiesBuilder();

        public Builder addAuthority(String id, String name) {
            authoritiesBuilder().add(new SimpleAuthority(id, name));
            return this;
        }

        public Builder addAuthority(String id) {
            return addAuthority(id, id);
        }

        public abstract Builder setIdn(String newIdn);

        public abstract Builder setEmail(String newEmail);

        public abstract Builder setUserType(String newUserType);

        public abstract Builder setPccOrgId(String newPccOrgId);

        public abstract Builder setCompIdno(String compIdno);

        public abstract Builder setIp(String newIp);

        public abstract SystemUser build();
    }
}
