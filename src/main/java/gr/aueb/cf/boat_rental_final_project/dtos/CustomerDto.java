package gr.aueb.cf.boat_rental_final_project.dtos;

import gr.aueb.cf.boat_rental_final_project.enums.CustomerRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private CustomerRole customerRole;

}

