package com.example.app.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
