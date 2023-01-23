
package com.mstech.gamesnatcherz.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** HARISH GADDAM */

public class ResponseProductReviews {

    @SerializedName("IsReviewd")
    @Expose
    private Integer isReviewd;
    @SerializedName("TotalRecords")
    @Expose
    private Integer totalRecords;
    @SerializedName("ReviewId")
    @Expose
    private Integer reviewId;
    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("Review")
    @Expose
    private String review;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedDateDisplay")
    @Expose
    private String createdDateDisplay;

    public Integer getIsReviewd() {
        return isReviewd;
    }

    public void setIsReviewd(Integer isReviewd) {
        this.isReviewd = isReviewd;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getMemberId() {
        return customerId;
    }

    public void setMemberId(Integer memberId) {
        this.customerId = memberId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
