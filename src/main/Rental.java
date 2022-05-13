package main;

public class Rental {
    private Movie movie;
    private int daysRented;
    private double amount;

    public Rental(Movie newmovie, int newdaysRented) {
        movie = newmovie;
        daysRented = newdaysRented;
        amount = -1;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getAmount() {
        if (this.amount == -1)
            calculateAmount();
        return this.amount;
    }

    private void calculateAmount() {
        switch (this.movie.getPriceCode()) {
            case REGULAR:
                this.amount = calculateRegularAmount();
                break;
            case NEW_RELEASE:
                this.amount = calculateNewReleaseAmount();
                break;
            case CHILDRENS:
                this.amount = calculateChildrenAmount();
                break;
        }
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

    public String toString() {
        String resultBody = "\t" + this.movie.getTitle() + "\t" + "\t" + this.daysRented + "\t"
                + String.valueOf(this.amount) + "\n";
        return resultBody;
    }

}