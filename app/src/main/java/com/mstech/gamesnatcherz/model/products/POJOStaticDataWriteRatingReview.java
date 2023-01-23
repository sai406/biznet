package com.mstech.gamesnatcherz.model.products;

/** HARISH GADDAM */

public class POJOStaticDataWriteRatingReview {

    private String strStars;
    private String strRatingBar;
    private String strRatingBarDescription;

    public POJOStaticDataWriteRatingReview(String strStars, String strRatingBar, String strRatingBarDescription) {
        this.strStars = strStars;
        this.strRatingBar = strRatingBar;
        this.strRatingBarDescription = strRatingBarDescription;
    }

    public String getStrStars() {
        return strStars;
    }

    public void setStrStars(String strStars) {
        this.strStars = strStars;
    }

    public String getStrRatingBar() {
        return strRatingBar;
    }

    public void setStrRatingBar(String strRatingBar) {
        this.strRatingBar = strRatingBar;
    }

    public String getStrRatingBarDescription() {
        return strRatingBarDescription;
    }

    public void setStrRatingBarDescription(String strRatingBarDescription) {
        this.strRatingBarDescription = strRatingBarDescription;
    }

    @Override
    public String toString() {
        return "POJOStaticDataWriteRatingReview{" +
                "strStars='" + strStars + '\'' +
                ", strRatingBar='" + strRatingBar + '\'' +
                ", strRatingBarDescription='" + strRatingBarDescription + '\'' +
                '}';
    }
}
