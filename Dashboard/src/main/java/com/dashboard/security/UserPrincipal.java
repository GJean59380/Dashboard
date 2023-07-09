package com.dashboard.security;

import com.dashboard.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {
    private final Long id;
    private final String email;
    private final String password;
    private Collection<? extends GrantedAuthority> roles;
    private Map<String, Object> attributes;

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roles1 -> authorities.add(new SimpleGrantedAuthority(roles1.getName())));
        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(), authorities
        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roles1 -> authorities.add(new SimpleGrantedAuthority(roles1.getName())));
        userPrincipal.setRoles(authorities);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }


}
