package gr.aueb.cf.boat_rental_final_project.model;


import gr.aueb.cf.boat_rental_final_project.enums.CustomerRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String surname;

    private String phone;

    private String email;

    private String name;

    private String password;

    private CustomerRole customerRole;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(customerRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}
