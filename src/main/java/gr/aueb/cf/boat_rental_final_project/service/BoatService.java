package gr.aueb.cf.boat_rental_final_project.service;

import gr.aueb.cf.boat_rental_final_project.dtos.BoatDto;
import gr.aueb.cf.boat_rental_final_project.exceptions.BoatNotfoundException;
import gr.aueb.cf.boat_rental_final_project.model.Boat;
import gr.aueb.cf.boat_rental_final_project.reporsitories.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoatService {
    private final BoatRepository boatRepository;

    @Autowired
    public BoatService(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public List<BoatDto> findAllBoats() {
        List<Boat> foundBoats = boatRepository.findAll();

        ArrayList<BoatDto> listToReturn = new ArrayList<>();

        if (foundBoats.isEmpty()) {
            return listToReturn;
        } else {
            for (Boat boat : foundBoats) {
                BoatDto toAddtoList = getBoatDtoFromBoat(boat);
                listToReturn.add(toAddtoList);
            }
            return listToReturn;
        }
    }

    public BoatDto updateBoat(BoatDto boatDto) {
        Optional<Boat> foundBoat = boatRepository.findById(boatDto.getId());
        if (foundBoat.isPresent()) {
            Boat boatToChange = foundBoat.get();
            boatToChange.setAvailability(boatDto.isAvailability());
            boatToChange.setCapacity(boatDto.getCapacity());
            boatToChange.setBrand(boatDto.getBrand());
            boatToChange.setModel(boatDto.getModel());
            boatToChange.setImage(boatToChange.getImage());
            boatToChange.setYear(boatDto.getYear());
            boatToChange.setPrice(boatDto.getPrice());

            Boat savedBoat = boatRepository.save(boatToChange);
            return getBoatDtoFromBoat(savedBoat);
        } else {
            throw new BoatNotfoundException("Boat for given id was not found");
        }
    }

    public boolean postBoat(BoatDto boatDto){
        try {
            Boat boat = new Boat();
            boat.setBrand(boatDto.getBrand());
            boat.setModel(boatDto.getModel());
            boat.setYear(boatDto.getYear());
            boat.setPrice(boatDto.getPrice());
            boat.setCapacity(boatDto.getCapacity());
            boat.setAvailability(boatDto.isAvailability());
            boat.setImage(boatDto.getImage());
            boatRepository.save(boat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<BoatDto> getAllBoats() {
        return boatRepository.findAll().stream().map(Boat::getBoatDto).collect(Collectors.toList());
    }

    public BoatDto findBoatById(Long id) {
        Optional<Boat> optionalBoat = boatRepository.findById(id);
        return optionalBoat.map(Boat::getBoatDto).orElse(null);
    }

    public boolean updateBoat(Long boatId, BoatDto boatDto) {
        Optional<Boat> optionalBoat = boatRepository.findById(boatId);
        if (optionalBoat.isPresent()) {
            Boat existingBoat = optionalBoat.get();
            if (boatDto.getImage() != null)
                existingBoat.setImage(boatDto.getImage());
            existingBoat.setAvailability(boatDto.isAvailability());
            existingBoat.setCapacity(boatDto.getCapacity());
            existingBoat.setBrand(boatDto.getBrand());
            existingBoat.setModel(boatDto.getModel());
            existingBoat.setYear(boatDto.getYear());
            existingBoat.setPrice(boatDto.getPrice());
            boatRepository.save(existingBoat);
            return true;
        } else {
            return false;
        }
    }

    public void deleteBoat(Long id) {
        boatRepository.deleteById(id);
    }

    private BoatDto getBoatDtoFromBoat(Boat boat) {
        return new BoatDto(
                boat.getId(), boat.getBrand(), boat.getModel(), boat.getYear(),
                boat.getPrice(), boat.getCapacity(), boat.getImage(), boat.isAvailability()
        );
    }

    public void updateBoatAvailabilityById(Long boatId, boolean available) {
        Boat savedBoat = boatRepository.findById(boatId).get();
        savedBoat.setAvailability(available);
        boatRepository.save(savedBoat);
    }
}
