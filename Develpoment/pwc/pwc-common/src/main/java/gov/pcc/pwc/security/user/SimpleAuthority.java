package gov.pcc.pwc.security.user;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import lombok.Getter;

/**
 * {@link Authority} 陽春實作。
 * <p>
 * 開發人員注意事項: 本物件是 Immutable 的。
 */
@Getter
public class SimpleAuthority implements Authority {

    private static final long serialVersionUID = 2173756357332277488L;

    private static final Collection<? extends Authority> EMPTY_SUB_AUTHORITIES = ImmutableList.of();

    private final String id;
    private final String name;
    private final Collection<? extends Authority> authorities;

    public SimpleAuthority(String id, String name) {
        this(id, name, EMPTY_SUB_AUTHORITIES);
    }

    public SimpleAuthority(String id, String name, Collection<? extends Authority> authorities) {
        this.id = requireNonNull(id, "Parameter \"id\" must not be null.");
        this.name = requireNonNull(name, "Parameter \"name\" must not be null.");

        Collection<? extends Authority> auths;
        requireNonNull(authorities, "Parameter \"authorities\" must not be null.");
        auths = ImmutableSet.copyOf(authorities);
        this.authorities = auths;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Collection<? extends Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        if (getAuthorities().isEmpty()) {
            return getId();
        } else {
            return getId() + ", sub-authorities=" + authorities;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority that = (Authority) o;
        return getId().equals(that.getId()) && getAuthorities().equals(that.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthorities());
    }
}
