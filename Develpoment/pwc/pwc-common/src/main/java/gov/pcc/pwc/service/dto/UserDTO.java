package gov.pcc.pwc.service.dto;

import java.util.Collections;
import java.util.Set;

import gov.pcc.pwc.security.user.Unit;

/**
 * A DTO representing a user, with only the public attributes.
 */
public class UserDTO {

    private String id;

    private String name;

    private Unit unit;

    private Set<String> roles;

    private Set<String> authorities;

    private String idn;

    private String email;

    private String userType;

    private String pccOrgId;

    private String compIdno;

    private String ip;

    public String getIdn() {
        return idn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Set<String> getRoles() {
        return roles != null ? roles : Collections.emptySet();
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getAuthorities() {
        return authorities != null ? authorities : Collections.emptySet();
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPccOrgId() {
        return pccOrgId;
    }

    public void setPccOrgId(String pccOrgId) {
        this.pccOrgId = pccOrgId;
    }

    public String getCompIdno() {
        return compIdno;
    }

    public void setCompIdno(String compIdno) {
        this.compIdno = compIdno;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
