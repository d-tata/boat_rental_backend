package gr.aueb.cf.boat_rental_final_project.controllers;

import gr.aueb.cf.boat_rental_final_project.service.AuthService.AuthService;
import gr.aueb.cf.boat_rental_final_project.dtos.AuthenticationRequest;
import gr.aueb.cf.boat_rental_final_project.dtos.AuthenticationResponse;
import gr.aueb.cf.boat_rental_final_project.dtos.CustomerDto;
import gr.aueb.cf.boat_rental_final_project.dtos.SignupRequest;
import gr.aueb.cf.boat_rental_final_project.model.Customer;
import gr.aueb.cf.boat_rental_final_project.reporsitories.CustomerRepository;
import gr.aueb.cf.boat_rental_final_project.service.jwt.UserService;
import gr.aueb.cf.boat_rental_final_project.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JWTUtil jwtUtil;

    private final CustomerRepository customerRepository;

    private final AuthService authService;

    @PostMapping("/signup")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<CustomerDto> signupCustomer(@RequestBody SignupRequest signupRequest) {
        CustomerDto createdCustomerDTO = authService.createCustomer(signupRequest);
        return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalCustomer.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setCustomerId(optionalCustomer.get().getId());
            authenticationResponse.setCustomerRole(optionalCustomer.get().getCustomerRole());
        }
        return authenticationResponse;
    }

}
