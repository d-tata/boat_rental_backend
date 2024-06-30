package gr.aueb.cf.boat_rental_final_project.reporsitories;

import gr.aueb.cf.boat_rental_final_project.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {
}
