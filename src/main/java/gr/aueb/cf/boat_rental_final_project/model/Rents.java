package gr.aueb.cf.boat_rental_final_project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Rents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long boat_id;

    private Long customer_id;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    private Long price;
}
