package com.mstech.gamesnatcherz.model.products;

/** HARISH GADDAM */

public class RequestAddProductReview {

    private int ReviewId;
    private int ProductId;
    private int MemberId;
    private String Review;
    private int Action;

    public RequestAddProductReview(int reviewId, int productId, int memberId, String review, int action) {
        ReviewId = reviewId;
        ProductId = productId;
        MemberId = memberId;
        Review = review;
        Action = action;
    }


    public int getReviewId() {
        return ReviewId;
    }

    public void setReviewId(int reviewId) {
        ReviewId = reviewId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getMemberId() {
        return MemberId;
    }

    public void setMemberId(int memberId) {
        MemberId = memberId;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public int getAction() {
        return Action;
    }

    public void setAction(int action) {
        Action = action;
    }
}
