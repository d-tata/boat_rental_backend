package gr.aueb.cf.boat_rental_final_project.dtos;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String password;
}
