package gr.aueb.cf.boat_rental_final_project.reporsitories;

import gr.aueb.cf.boat_rental_final_project.enums.CustomerRole;
import gr.aueb.cf.boat_rental_final_project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    Customer findByCustomerRole(CustomerRole customerRole);
}
