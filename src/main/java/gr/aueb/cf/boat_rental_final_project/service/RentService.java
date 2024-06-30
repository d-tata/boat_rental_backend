package gr.aueb.cf.boat_rental_final_project.service;

import gr.aueb.cf.boat_rental_final_project.dtos.RentsDto;
import gr.aueb.cf.boat_rental_final_project.exceptions.RentNotFoundException;
import gr.aueb.cf.boat_rental_final_project.model.Rents;
import gr.aueb.cf.boat_rental_final_project.reporsitories.RentRepository;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;

    private BoatService boatService;

    @Autowired
    public RentService(RentRepository rentRepository, BoatService boatService) {
        this.rentRepository = rentRepository;
        this.boatService = boatService;
    }

    public void deleteRent(Long id) {
        Rents foundRent = rentRepository.findById(id).get();
        boatService.updateBoatAvailabilityById(foundRent.getBoat_id(), true);
        rentRepository.deleteById(id);
    }


    public List<RentsDto> findAllRents() {
        List<Rents> foundRents = rentRepository.findAll();

        ArrayList<RentsDto> listToReturn = new ArrayList<>();

        if(foundRents.isEmpty()) {
            return listToReturn;
        } else {
            for (Rents rents:foundRents) {
                RentsDto rentDto = getRentDtoFromRent(rents);
                listToReturn.add(rentDto);
            }
            return listToReturn;
        }
    }
    public RentsDto findRentsById(Long id) {
        Optional<Rents> rent =rentRepository.findById(id);
        if (rent.isPresent()) {
            return getRentDtoFromRent(rent.get());
        }else {
            throw new RentNotFoundException("Rent with given id was not found");
        }
    }

    public RentsDto addRents(RentsDto rentsDto) {
        Rents rentToSave = new Rents();
        rentToSave.setActive(rentsDto.isActive());
        rentToSave.setBoat_id(rentsDto.getBoat_id());
        rentToSave.setCustomer_id(rentsDto.getCustomer_id());
        rentToSave.setStartDate(rentsDto.getStartDate());
        rentToSave.setEndDate(rentsDto.getEndDate());

        Rents savedRent = rentRepository.save(rentToSave);


        boatService.updateBoatAvailabilityById(savedRent.getBoat_id(), false);


        return getRentDtoFromRent(savedRent);
    }

    private RentsDto getRentDtoFromRent(Rents rents) {
        RentsDto rentDtoToReturn = new RentsDto();
        rentDtoToReturn.setActive(rents.isActive());
        rentDtoToReturn.setBoat_id(rents.getBoat_id());
        rentDtoToReturn.setCustomer_id(rents.getCustomer_id());
        rentDtoToReturn.setStartDate(rents.getStartDate());
        rentDtoToReturn.setEndDate(rents.getEndDate());
        rentDtoToReturn.setId(rents.getId());

        return rentDtoToReturn;
    }

}


