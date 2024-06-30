package gr.aueb.cf.boat_rental_final_project.model;

import gr.aueb.cf.boat_rental_final_project.dtos.BoatDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Table(name = "boats")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private String year;
    private String price;
    private String capacity;
    private byte[] image;
    private boolean availability;


    public static BoatDto getBoatDto(Boat boat) {
        return new  BoatDto (
                boat.getId(), boat.getBrand(), boat.getModel(), boat.getYear(),
                boat.getPrice(), boat.getCapacity(), boat.getImage(), boat.isAvailability()
        );
    }

}
