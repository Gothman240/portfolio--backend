package com.fede.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    @Size(min = 4, max = 12)
    String username;
    @NotEmpty
    String password;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Profile profile;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = { @UniqueConstraint(columnNames = { "person_id", "role_id" }) })
    private List<Rol> roles = new ArrayList<>();

    public Person() {
    }

    public Person( String username, String password ) {
        this.username = username;
        this.password = password;
    }


}
