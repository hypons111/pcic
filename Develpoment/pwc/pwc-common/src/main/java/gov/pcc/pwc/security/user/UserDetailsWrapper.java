package gov.pcc.pwc.security.user;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;

public class UserDetailsWrapper implements UserDetails, CredentialsContainer {

    public static final String AUTHORITY_PREFIX = "AUTHORITY_";
    public static final String ROLE_PREFIX = "ROLE_";

    private static final long serialVersionUID = -6676004862711198398L;

    protected final User user;

    private transient LoadingCache<String, Collection<GrantedAuthority>> authorityCache;

    private String password;

    public UserDetailsWrapper(User user) {
        this.user = requireNonNull(user, "Parameter \"user\" must not be null.");
        initAuthorityCache();
    }

    private void initAuthorityCache() {
        this.authorityCache =
            CacheBuilder
                .newBuilder()
                .build(
                    new CacheLoader<String, Collection<GrantedAuthority>>() {
                        @Override
                        public Collection<GrantedAuthority> load(String key) throws Exception {
                            checkArgument(
                                key.equals(getAuthoritiesOwnerId()),
                                "Cache key %s doesn't match current authority owner id %s.",
                                key,
                                getAuthoritiesOwnerId()
                            );

                            ImmutableList.Builder<GrantedAuthority> builder = ImmutableList.builder();
                            for (Authority authority : user.getAuthorities()) {
                                // TODO 處理子內容
                                builder.add(new SimpleGrantedAuthority(AUTHORITY_PREFIX + authority.getId()));
                            }
                            for (Role role : user.getRoles()) {
                                builder.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getId()));
                            }
                            return builder.build();
                        }
                    }
                );
    }

    private Object readResolve() {
        initAuthorityCache();
        return this;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorityCache.getUnchecked(getAuthoritiesOwnerId());
    }

    private String getAuthoritiesOwnerId() {
        return user.getId();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        // return password if set
        if (password != null) {
            return password;
        }

        // default to null
        return null;
    }

    @Override
    public String getUsername() {
        return getAuthoritiesOwnerId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
        if (user instanceof CredentialsContainer) {
            ((CredentialsContainer) user).eraseCredentials();
        }
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
