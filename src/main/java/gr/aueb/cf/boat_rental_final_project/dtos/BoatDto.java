package gr.aueb.cf.boat_rental_final_project.dtos;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class BoatDto {

    private Long id;

    private String brand;

    private String model;

    @Nullable
    private String year;

    private String price;

    private String capacity;


    private byte[] image;


    private boolean availability;

}
