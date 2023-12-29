package com.fede.backend.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String username;
    String password;
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Profile profile;

    public Person() {
    }

    public Person( String username, String password ) {
        this.username = username;
        this.password = password;
    }


}
