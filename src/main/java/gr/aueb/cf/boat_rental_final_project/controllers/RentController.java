package gr.aueb.cf.boat_rental_final_project.controllers;

import gr.aueb.cf.boat_rental_final_project.dtos.RentsDto;
import gr.aueb.cf.boat_rental_final_project.model.Rents;
import gr.aueb.cf.boat_rental_final_project.reporsitories.RentRepository;
import gr.aueb.cf.boat_rental_final_project.service.RentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;


    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<RentsDto>> getAllRents() {
        List<RentsDto> rents = rentService.findAllRents();
        System.out.println(rents);
        return new ResponseEntity<>(rents, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<RentsDto> getAllRentsById(@PathVariable("id") Long id) {
        RentsDto rents = rentService.findRentsById(id);
        return new ResponseEntity<>(rents, HttpStatus.OK);
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<RentsDto> addRent(@ModelAttribute RentsDto rents) {
        RentsDto newRent = rentService.addRents(rents);
        return new ResponseEntity<>(newRent, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> deleteRent (@PathVariable("id") Long id){
        rentService.deleteRent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

}