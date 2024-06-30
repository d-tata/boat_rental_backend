package gr.aueb.cf.boat_rental_final_project.exceptions;

public class CustomerNotfoundException extends RuntimeException {
    public CustomerNotfoundException(String message) {
        super (message);
    }
}
