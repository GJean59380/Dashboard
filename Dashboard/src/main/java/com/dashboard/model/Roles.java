package com.dashboard.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Roles {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    public Set<User> getUsers() {
        return users;
    }

}