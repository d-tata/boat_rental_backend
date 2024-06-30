package gr.aueb.cf.boat_rental_final_project.reporsitories;

import gr.aueb.cf.boat_rental_final_project.model.Rents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rents, Long> {

}
