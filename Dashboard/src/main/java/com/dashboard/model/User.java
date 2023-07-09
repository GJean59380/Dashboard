package com.dashboard.model;

import com.dashboard.dto.user.response.UserDto;
import com.dashboard.dto.userWidget.UserWidgetDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @OneToMany
    private Set<UserWidget> widgetList =new LinkedHashSet<>();

    @Column
    @ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private Set<Roles> roles = new HashSet<>();
    @Column
    private String providerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

/*    public Set<Widget> getWidgets() {
        if (widgets == null) {
            return new HashSet<>();
        }
        return widgets;
    }

    public void setWidgets(Set<Widget> widgets) {
        this.widgets = widgets;
    }*/


    public Set<UserWidget> getWidgetList() {
        return widgetList;
    }

    public void setWidgetList(Set<UserWidget> userWidgets) {
        this.widgetList = userWidgets;
    }

    public Set<Roles> getRoles() {
        if (roles == null) {
            return new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Roles> addRole(Roles role) {
        this.roles.add(role);
        return this.roles;
    }

    public UserDto getUserDto() {
        Set<String> roles = new HashSet<>();
        Set<UserWidgetDto> widgets = new HashSet<>();
        this.getRoles().forEach(roles1 -> roles.add(roles1.getName()));
        this.getWidgetList().forEach(userWidget -> widgets.add(userWidget.userWidgetToDto()));
        return new UserDto(this.getId(), this.getName(), this.getEmail(), this.getImageUrl(), this.getEmailVerified(), this.getProvider(), widgets, roles, this.getProviderId());
    }


}
