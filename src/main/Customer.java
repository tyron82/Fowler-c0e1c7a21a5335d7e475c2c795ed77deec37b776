package main;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new LinkedList<>();

    public Customer(String newname) {
        name = newname;
    };

    public void addRental(Rental arg) {
        rentals.add(arg);
    };

    public List<Rental> getRentals() {
        return this.rentals;
    }

    public String getName() {
        return name;
    };

}
