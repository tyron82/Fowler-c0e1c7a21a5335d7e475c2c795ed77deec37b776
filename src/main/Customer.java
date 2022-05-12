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
            frequentRenterPoints += additionalFrequentRenterPoints(each);
            result += createResultBody(thisAmount, each);
            totalAmount += thisAmount;
        }
        result += createResultFooter(totalAmount, frequentRenterPoints);
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

    private String createResultBody(double thisAmount, Rental each) {
        String resultBody = "\t" + each.getMovie().getTitle() + "\t" + "\t" + each.getDaysRented() + "\t"
                + String.valueOf(thisAmount) + "\n";
        return resultBody;
    }

    private String createResultFooter(double totalAmount, int frequentRenterPoints) {
        String resultFooter = "Amount owed is " + String.valueOf(totalAmount) + "\n";
        resultFooter += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return resultFooter;
    }

    private double amountFor(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount = calculateRegularAmount(each);
                break;
            case Movie.NEW_RELEASE:
                thisAmount = calculateNewReleaseAmount(each);
                break;
            case Movie.CHILDRENS:
                thisAmount = calculateChildrenAmount(each);
                break;
        }
        return thisAmount;
    }

    private double calculateRegularAmount(Rental each) {
        double thisAmount = 2;
        if (each.getDaysRented() > 2)
            thisAmount += (each.getDaysRented() - 2) * 1.5;
        return thisAmount;
    }

    private double calculateNewReleaseAmount(Rental each) {
        double thisAmount = each.getDaysRented() * 3;
        return thisAmount;
    }

    private double calculateChildrenAmount(Rental each) {
        double thisAmount = 1.5;
        if (each.getDaysRented() > 3)
            thisAmount += (each.getDaysRented() - 3) * 1.5;
        return thisAmount;
    }

}
