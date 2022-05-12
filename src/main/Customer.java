package main;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector rentals = new Vector();

    public Customer(String newname) {
        name = newname;
    };

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    };

    public String getName() {
        return name;
    };

    public String statement() {
        double totalRentalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();
        String result = createResultHeader();

        while (enum_rentals.hasMoreElements()) {
            double rentalAmount = 0;
            Rental rental = (Rental) enum_rentals.nextElement();
            rentalAmount = amountFor(rental);
            frequentRenterPoints += additionalFrequentRenterPoints(rental);
            result += createResultBody(rentalAmount, rental);
            totalRentalAmount += rentalAmount;
        }
        result += createResultFooter(totalRentalAmount, frequentRenterPoints);
        return result;
    }

    private int additionalFrequentRenterPoints(Rental each) {
        int additionalFrequentRenterPoints = 1;
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
            additionalFrequentRenterPoints++;
        return additionalFrequentRenterPoints;
    }

    private String createResultHeader() {
        String resultHeader = "Rental Record for " + this.getName() + "\n";
        resultHeader += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";
        return resultHeader;
    }

    private String createResultBody(double rentalAmount, Rental each) {
        String resultBody = "\t" + each.getMovie().getTitle() + "\t" + "\t" + each.getDaysRented() + "\t"
                + String.valueOf(rentalAmount) + "\n";
        return resultBody;
    }

    private String createResultFooter(double totalAmount, int frequentRenterPoints) {
        String resultFooter = "Amount owed is " + String.valueOf(totalAmount) + "\n";
        resultFooter += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return resultFooter;
    }

    private double amountFor(Rental rental) {
        double rentalAmount = 0;
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                rentalAmount = calculateRegularAmount(rental);
                break;
            case Movie.NEW_RELEASE:
                rentalAmount = calculateNewReleaseAmount(rental);
                break;
            case Movie.CHILDRENS:
                rentalAmount = calculateChildrenAmount(rental);
                break;
        }
        return rentalAmount;
    }

    private double calculateRegularAmount(Rental rental) {
        double rentalRegularAmount = 2;
        if (rental.getDaysRented() > 2)
            rentalRegularAmount += (rental.getDaysRented() - 2) * 1.5;
        return rentalRegularAmount;
    }

    private double calculateNewReleaseAmount(Rental rental) {
        double rentalNewReleaseAmount = rental.getDaysRented() * 3;
        return rentalNewReleaseAmount;
    }

    private double calculateChildrenAmount(Rental rental) {
        double rentalChildrenAmount = 1.5;
        if (rental.getDaysRented() > 3)
            rentalChildrenAmount += (rental.getDaysRented() - 3) * 1.5;
        return rentalChildrenAmount;
    }

}
