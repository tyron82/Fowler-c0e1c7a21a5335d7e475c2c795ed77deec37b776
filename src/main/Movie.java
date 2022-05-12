package main;

public class Movie {
    public enum priceCodes {
        REGULAR, NEW_RELEASE, CHILDRENS
    };

    private String title;
    private priceCodes priceCode;

    public Movie(String newtitle, int newpriceCode) {
        title = newtitle;
        priceCode = priceCodes.values()[newpriceCode];
    }

    public priceCodes getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = priceCodes.values()[arg];
    }

    public String getTitle() {
        return title;
    };
}