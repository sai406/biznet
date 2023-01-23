
package com.mstech.gamesnatcherz.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** HARISH GADDAM */

public class AttributeValue {

    @SerializedName("AttributeValueId")
    @Expose
    private Integer attributeValueId;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("AttributeId")
    @Expose
    private Integer attributeId;
    @SerializedName("AttributeValue")
    @Expose
    private String attributeValue;

    public Integer getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(Integer attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

}
