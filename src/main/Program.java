package main;

import main.Movie.priceCodes;

public class Program {

    public static void main(String args[]) {
        String result;
        System.out.println("Welcome to the Movie Store");
        Movie m1 = new Movie("movie1", 1);
        Movie m2 = new Movie("movie2", 2);
        Rental r1 = new Rental(m1, 10);
        Rental r2 = new Rental(m2, 5);
        Customer c1 = new Customer("joe");
        c1.addRental(r1);
        c1.addRental(r2);
        System.out.println("Let's get the Statement");
        result = statement(c1);
        System.out.println(result);
    }

    public static String statement(Customer customer) {
        double totalRentalAmount = 0;
        int frequentRenterPoints = 0;
        String result = createResultHeader(customer);

        for (Rental rental : customer.getRentals()) {
            double rentalAmount = 0;
            rentalAmount = rental.getAmount();
            frequentRenterPoints += additionalFrequentRenterPoints(rental);
            result += rental.toString();
            totalRentalAmount += rentalAmount;
        }

        result += createResultFooter(totalRentalAmount, frequentRenterPoints);
        return result;
    }

    private static int additionalFrequentRenterPoints(Rental rental) {
        int additionalFrequentRenterPoints = 1;
        if ((rental.getMovie().getPriceCode() == priceCodes.NEW_RELEASE) && rental.getDaysRented() > 1)
            additionalFrequentRenterPoints++;
        return additionalFrequentRenterPoints;
    }

    private static String createResultHeader(Customer customer) {
        String resultHeader = "Rental Record for " + customer.getName() + "\n";
        resultHeader += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";
        return resultHeader;
    }

    private static String createResultFooter(double totalAmount, int frequentRenterPoints) {
        String resultFooter = "Amount owed is " + String.valueOf(totalAmount) + "\n";
        resultFooter += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return resultFooter;
    }
}
