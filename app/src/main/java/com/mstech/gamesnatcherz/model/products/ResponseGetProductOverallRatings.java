
package com.mstech.gamesnatcherz.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** HARISH GADDAM */

public class ResponseGetProductOverallRatings {

    @SerializedName("TotalRating")
    @Expose
    private float totalRating;
    @SerializedName("TotalMembers")
    @Expose
    private Integer totalMembers;

    public float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(float totalRating) {
        this.totalRating = totalRating;
    }

    public Integer getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(Integer totalMembers) {
        this.totalMembers = totalMembers;
    }

}
