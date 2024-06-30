package gr.aueb.cf.boat_rental_final_project.dtos;

import gr.aueb.cf.boat_rental_final_project.enums.CustomerRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private CustomerRole customerRole;
    private Long customerId;
}
