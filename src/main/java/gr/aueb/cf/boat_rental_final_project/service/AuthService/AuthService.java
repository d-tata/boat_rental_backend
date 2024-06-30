package gr.aueb.cf.boat_rental_final_project.service.AuthService;

import gr.aueb.cf.boat_rental_final_project.dtos.CustomerDto;
import gr.aueb.cf.boat_rental_final_project.dtos.SignupRequest;
import gr.aueb.cf.boat_rental_final_project.enums.CustomerRole;
import gr.aueb.cf.boat_rental_final_project.model.Customer;
import gr.aueb.cf.boat_rental_final_project.reporsitories.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepository customerRepository;

    @PostConstruct
    public void createAdminAccount() {
        Customer adminAccount = customerRepository.findByCustomerRole(CustomerRole.ADMIN);
        if (adminAccount == null){
            Customer newAdminAccount = new Customer();
            newAdminAccount.setName("Admin");
            newAdminAccount.setEmail("admin@admin.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("12345"));
            newAdminAccount.setCustomerRole(CustomerRole.ADMIN);
            customerRepository.save(newAdminAccount);
            System.out.println("Admin account is created");
        }
    }

    public CustomerDto createCustomer(SignupRequest signupRequest) {
        if (existCustomerByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Customer already signed up");
        }

        Customer customer = new Customer();
        customer.setName(signupRequest.getName());
        customer.setEmail(signupRequest.getEmail());
        customer.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        customer.setPhone(signupRequest.getPhoneNumber());
        customer.setSurname(signupRequest.getSurname());
        customer.setCustomerRole(CustomerRole.CUSTOMER);
        Customer createdCustomer = customerRepository.save(customer);
        if (createdCustomer.getId() == null) {
            throw new RuntimeException("Customer was not created");
        }
        return getCustomerDtoFromCustomer(createdCustomer);
    }

    private boolean existCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }


    private CustomerDto getCustomerDtoFromCustomer(Customer customer) {
        return new CustomerDto(
                customer.getId(), customer.getName(), customer.getSurname(),
                customer.getPhone(), customer.getEmail(), customer.getCustomerRole()
        );
    }


}