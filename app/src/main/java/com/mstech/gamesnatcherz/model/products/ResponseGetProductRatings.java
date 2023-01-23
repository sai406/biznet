
package com.mstech.gamesnatcherz.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** HARISH GADDAM */

public class ResponseGetProductRatings {

    @SerializedName("RatingId")
    @Expose
    private Integer ratingId;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("MemberId")
    @Expose
    private Integer memberId;
    @SerializedName("Rating")
    @Expose
    private Float rating;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedDateDisplay")
    @Expose
    private String createdDateDisplay;

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDateDisplay() {
        return createdDateDisplay;
    }

    public void setCreatedDateDisplay(String createdDateDisplay) {
        this.createdDateDisplay = createdDateDisplay;
    }

}
