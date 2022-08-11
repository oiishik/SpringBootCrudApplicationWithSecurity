package com.project.SpringCrud.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="roles")
public class User_Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String role;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "userRoles",fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public User_Roles(long id, String role) {
        this.id = id;
        this.role = role;
    }
}

