package gov.pcc.pwc.security.user;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class SimpleRole implements Role {

    private static final long serialVersionUID = 1858155624305966397L;

    private String id;
    private String name;

    public SimpleRole(String id, String name) {
        this.id = requireNonNull(id, "Parameter \"id\" must not be null.");
        this.name = requireNonNull(name, "Parameter \"name\" must not be null.");
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role that = (Role) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
