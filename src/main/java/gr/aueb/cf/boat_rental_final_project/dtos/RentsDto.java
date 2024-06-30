package gr.aueb.cf.boat_rental_final_project.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Getter
@Setter
public class RentsDto {

    private Long id;

    private Long boat_id;

    private Long customer_id;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long price;

    private boolean active;


}
