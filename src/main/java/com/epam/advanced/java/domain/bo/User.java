package com.epam.advanced.java.domain.bo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String password;
    private boolean enabled;
}
