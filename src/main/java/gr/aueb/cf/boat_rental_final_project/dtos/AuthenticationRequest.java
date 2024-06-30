package gr.aueb.cf.boat_rental_final_project.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
