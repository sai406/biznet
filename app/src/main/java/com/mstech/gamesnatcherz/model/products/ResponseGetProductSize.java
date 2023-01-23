
package com.mstech.gamesnatcherz.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/** HARISH GADDAM */

public class ResponseGetProductSize {

    @SerializedName("AttributeId")
    @Expose
    private Integer attributeId;
    @SerializedName("Attribute")
    @Expose
    private String attribute;
    @SerializedName("AttributeValues")
    @Expose
    private ArrayList<AttributeValue> attributeValues = null;

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public ArrayList<AttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(ArrayList<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

}
