package gr.aueb.cf.boat_rental_final_project.service.jwt;

import gr.aueb.cf.boat_rental_final_project.reporsitories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomerRepository customerRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return customerRepository.findByEmail(username)
                        .orElseThrow(() -> new RuntimeException("User Not Found"));
            }
        };
    }


}
