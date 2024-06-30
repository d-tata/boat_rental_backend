package gr.aueb.cf.boat_rental_final_project.controllers;

import gr.aueb.cf.boat_rental_final_project.dtos.BoatDto;
import gr.aueb.cf.boat_rental_final_project.dtos.CustomerDto;
import gr.aueb.cf.boat_rental_final_project.model.Boat;
import gr.aueb.cf.boat_rental_final_project.model.Customer;
import gr.aueb.cf.boat_rental_final_project.service.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.findAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerDto> getAllCustomersById(@PathVariable("id") Long id) {
        CustomerDto customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customer) {
        CustomerDto newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customer) {
        CustomerDto updateCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
