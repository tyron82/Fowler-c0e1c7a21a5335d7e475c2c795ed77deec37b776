package tests;

import org.junit.Assert;
import org.junit.Test;

import main.*;

public class Tests {

        @Test
        public void testStatement() {
                String expectedString = "Rental Record for joe\n";
                expectedString += "\tTitle\t\tDays\tAmount\n";
                expectedString += "\tmovie1\t\t10\t30.0\n";
                expectedString += "\tmovie2\t\t5\t4.5\n";
                expectedString += "Amount owed is 34.5\n";
                expectedString += "You earned 3 frequent renter points";

                String actuallString;
                Movie m1 = new Movie("movie1", 1);
                Movie m2 = new Movie("movie2", 2);
                Rental r1 = new Rental(m1, 10);
                Rental r2 = new Rental(m2, 5);
                Customer c1 = new Customer("joe");

                c1.addRental(r1);
                c1.addRental(r2);

                actuallString = Program.statement(c1);

                Assert.assertEquals(expectedString, actuallString);
        }
}
