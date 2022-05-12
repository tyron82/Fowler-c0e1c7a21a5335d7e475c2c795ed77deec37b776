package main;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie newmovie, int newdaysRented) {
        movie = newmovie;
        daysRented = newdaysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double amount() {
        double rentalAmount = 0;
        switch (this.movie.getPriceCode()) {
            case Movie.REGULAR:
                rentalAmount = calculateRegularAmount();
                break;
            case Movie.NEW_RELEASE:
                rentalAmount = calculateNewReleaseAmount();
                break;
            case Movie.CHILDRENS:
                rentalAmount = calculateChildrenAmount();
                break;
        }
        return rentalAmount;
    }

    private double calculateRegularAmount() {
        double rentalRegularAmount = 2;
        if (this.daysRented > 2)
            rentalRegularAmount += (this.daysRented - 2) * 1.5;
        return rentalRegularAmount;
    }

    private double calculateNewReleaseAmount() {
        double rentalNewReleaseAmount = this.daysRented * 3;
        return rentalNewReleaseAmount;
    }

    private double calculateChildrenAmount() {
        double rentalChildrenAmount = 1.5;
        if (this.daysRented > 3)
            rentalChildrenAmount += (this.daysRented - 3) * 1.5;
        return rentalChildrenAmount;
    }

}