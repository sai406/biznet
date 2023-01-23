package com.mstech.gamesnatcherz.model.products;

/** HARISH GADDAM */

public class RequestAddProductRating {

    private int RatingId;
    private int ProductId;
    private int CustomerId;
    private String Rating;

    public RequestAddProductRating(int ratingId, int productId, int memberId, String rating) {
        RatingId = ratingId;
        ProductId = productId;
        CustomerId = memberId;
        Rating = rating;
    }

    public int getRatingId() {
        return RatingId;
    }

    public void setRatingId(int ratingId) {
        RatingId = ratingId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getMemberId() {
        return CustomerId;
    }

    public void setMemberId(int memberId) {
        CustomerId = memberId;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }
}
