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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();
        String result = createResultHeader();

        while (enum_rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) enum_rentals.nextElement();
            thisAmount = amountFor(each);
            frequentRenterPoints = updateFrequentRenterPoints(frequentRenterPoints, each);
            result = createResultBody(result, thisAmount, each);
            totalAmount += thisAmount;
        }
        result = createResultFooter(totalAmount, frequentRenterPoints, result);
        return result;
    }

    private int updateFrequentRenterPoints(int frequentRenterPoints, Rental each) {
        frequentRenterPoints++;
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private String createResultHeader() {
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";
        return result;
    }

    private String createResultBody(String result, double thisAmount, Rental each) {
        result += "\t" + each.getMovie().getTitle() + "\t" + "\t" + each.getDaysRented() + "\t"
                + String.valueOf(thisAmount) + "\n";
        return result;
    }

    private String createResultFooter(double totalAmount, int frequentRenterPoints, String result) {
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    private double amountFor(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount = calculateRegularAmount(each, thisAmount);
                break;
            case Movie.NEW_RELEASE:
                thisAmount = calculateNewReleaseAmount(each, thisAmount);
                break;
            case Movie.CHILDRENS:
                thisAmount = calculateChildrenAmount(each, thisAmount);
                break;
        }
        return thisAmount;
    }

    private double calculateRegularAmount(Rental each, double thisAmount) {
        thisAmount += 2;
        if (each.getDaysRented() > 2)
            thisAmount += (each.getDaysRented() - 2) * 1.5;
        return thisAmount;
    }

    private double calculateNewReleaseAmount(Rental each, double thisAmount) {
        thisAmount += each.getDaysRented() * 3;
        return thisAmount;
    }

    private double calculateChildrenAmount(Rental each, double thisAmount) {
        thisAmount += 1.5;
        if (each.getDaysRented() > 3)
            thisAmount += (each.getDaysRented() - 3) * 1.5;
        return thisAmount;
    }

}
