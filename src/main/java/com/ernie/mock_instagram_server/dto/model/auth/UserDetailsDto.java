package com.ernie.mock_instagram_server.dto.model.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsDto implements UserDetails {

    private String username;

    private String password;

    private Boolean accountExpired;

    private Boolean accountLocked;

    private Boolean credentialsExpired;

    private Boolean enabled;

    private List<GrantedAuthority> grantedAuthorities;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
