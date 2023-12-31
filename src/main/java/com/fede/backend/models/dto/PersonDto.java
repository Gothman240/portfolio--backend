package com.fede.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {

    private Long id;
    private String username;

    public PersonDto() {
    }

    public PersonDto( Long id, String username ) {
        this.id = id;
        this.username = username;
    }
}
