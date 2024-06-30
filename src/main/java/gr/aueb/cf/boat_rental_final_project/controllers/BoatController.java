package gr.aueb.cf.boat_rental_final_project.controllers;


import gr.aueb.cf.boat_rental_final_project.dtos.BoatDto;
import gr.aueb.cf.boat_rental_final_project.service.BoatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class BoatController {
    private final BoatService boatService;

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @PostMapping("/api/admin/boat")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> postBoat(@ModelAttribute BoatDto boatDto) {
        boolean success = boatService.postBoat(boatDto);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/api/admin/boats")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> getAllBoats() {
        return ResponseEntity.ok(boatService.getAllBoats());
    }


    @DeleteMapping("/api/admin/boats/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> deleteBoat(@PathVariable Long id) {
        boatService.deleteBoat(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/boat/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<BoatDto> getBoatById(@PathVariable("id") Long id) {
        BoatDto boatDto = boatService.findBoatById(id);
        return  ResponseEntity.ok(boatDto);
    }

    @PutMapping("/api/admin/boat/{boatId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Void> updateBoat(@PathVariable Long boatId, @ModelAttribute BoatDto boatDto) throws IOException {
        try {
            boolean success = boatService.updateBoat(boatId, boatDto);
            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/boat/available")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<BoatDto>> getAvailableBoats() {
        List<BoatDto> boats = boatService.findAllBoats();
        return new ResponseEntity<>(boats.stream().filter(BoatDto::isAvailability).toList(),HttpStatus.OK);
    }


    @GetMapping("/boat/find/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<BoatDto> getAllBoatById(@PathVariable("id") Long id) {
        BoatDto boat = boatService.findBoatById(id);
        return new ResponseEntity<>(boat, HttpStatus.OK);
    }

    @PutMapping("/update")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<BoatDto> updateBoat(@RequestBody BoatDto boat) {
        BoatDto updateBoat = boatService.updateBoat(boat);
        return new ResponseEntity<>(updateBoat, HttpStatus.OK);
    }

}
