package gr.aueb.cf.boat_rental_final_project.service;

import gr.aueb.cf.boat_rental_final_project.dtos.CustomerDto;
import gr.aueb.cf.boat_rental_final_project.exceptions.CustomerNotfoundException;
import gr.aueb.cf.boat_rental_final_project.model.Customer;
import gr.aueb.cf.boat_rental_final_project.reporsitories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> findAllCustomers() {
        List<Customer> foundcustomer = customerRepository.findAll();

        ArrayList<CustomerDto> listToReturn = new ArrayList<>();

        if (foundcustomer.isEmpty()) {
            return listToReturn;
        } else {
            for (Customer customer : foundcustomer) {
                CustomerDto toAddtoList = getCustomerDtoFromCustomer(customer);
                listToReturn.add(toAddtoList);
            }
            return listToReturn;
        }
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> foundCustomer = customerRepository.findById(customerDto.getId());
        if (foundCustomer.isPresent()) {
            Customer customerToChange = foundCustomer.get();
            customerToChange.setName(customerDto.getName());
            customerToChange.setSurname(customerDto.getSurname());
            customerToChange.setPhone(customerDto.getPhone());
            customerToChange.setEmail(customerDto.getEmail());


            Customer savedCustomer = customerRepository.save(customerToChange);
            return getCustomerDtoFromCustomer(savedCustomer);
        }else{
            throw new CustomerNotfoundException("Customer for the given id was not found ");

        }
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer toSave = getCustomerFromCustomerDto(customerDto);


        Customer savedCustomer = customerRepository.save(toSave);
        return getCustomerDtoFromCustomer(savedCustomer);
    }



    public CustomerDto findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            return getCustomerDtoFromCustomer(customer.get());

        }else{
            throw new CustomerNotfoundException( "Customer with id:" + id + " is not found");
        }

    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


    private CustomerDto getCustomerDtoFromCustomer(Customer customer) {
        return new CustomerDto (
                customer.getId(),customer.getName(),customer.getSurname(),
                customer.getPhone(),customer.getEmail(),customer.getCustomerRole()
        );
    }

    private Customer getCustomerFromCustomerDto(CustomerDto customerDto) {
        Customer customerToReturn = new Customer();
        customerToReturn.setName(customerDto.getName());
        customerToReturn.setSurname(customerDto.getSurname());
        customerToReturn.setPhone(customerDto.getPhone());
        customerToReturn.setEmail(customerDto.getEmail());

        return customerToReturn;
    }

}

