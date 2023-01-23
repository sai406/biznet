
package com.mstech.gamesnatcherz.model.products.product_items_in_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * HARISH GADDAM
 */

public class CartItem implements Serializable {

    @SerializedName("CartId")
    @Expose
    private Integer cartId;
    @SerializedName("CartItemId")
    @Expose
    private Integer cartItemId;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("MemberId")
    @Expose
    private Integer memberId;
    @SerializedName("OrganisationId")
    @Expose
    private Integer organisationId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductImage")
    @Expose
    private String productImage;
    @SerializedName("ProductImagePath")
    @Expose
    private String productImagePath;
    @SerializedName("Qty")
    @Expose
    private Integer qty;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("DisplayPrice")
    @Expose
    private String displayPrice;
    @SerializedName("ProductPrice")
    @Expose
    private double productPrice;
    @SerializedName("DisplayProductPrice")
    @Expose
    private String displayProductPrice;
    @SerializedName("ProductOfferPrice")
    @Expose
    private String productOfferPrice;
    @SerializedName("DisplayProductOfferPrice")
    @Expose
    private String displayProductOfferPrice;
    @SerializedName("AddedDate")
    @Expose
    private String addedDate;
    @SerializedName("AddedDateDisplay")
    @Expose
    private String addedDateDisplay;
    @SerializedName("IsAddressRequired")
    @Expose
    private Integer isAddressRequired;
    @SerializedName("ShippingCost")
    @Expose
    private String shippingCost;
    @SerializedName("DealAttributeValues")
    @Expose
    private String dealAttributeValues;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
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

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDisplayProductPrice() {
        return displayProductPrice;
    }

    public void setDisplayProductPrice(String displayProductPrice) {
        this.displayProductPrice = displayProductPrice;
    }

    public String getProductOfferPrice() {
        return productOfferPrice;
    }

    public void setProductOfferPrice(String productOfferPrice) {
        this.productOfferPrice = productOfferPrice;
    }

    public String getDisplayProductOfferPrice() {
        return displayProductOfferPrice;
    }

    public void setDisplayProductOfferPrice(String displayProductOfferPrice) {
        this.displayProductOfferPrice = displayProductOfferPrice;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getAddedDateDisplay() {
        return addedDateDisplay;
    }

    public void setAddedDateDisplay(String addedDateDisplay) {
        this.addedDateDisplay = addedDateDisplay;
    }

    public Integer getIsAddressRequired() {
        return isAddressRequired;
    }

    public void setIsAddressRequired(Integer isAddressRequired) {
        this.isAddressRequired = isAddressRequired;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getDealAttributeValues() {
        return dealAttributeValues;
    }

    public void setDealAttributeValues(String dealAttributeValues) {
        this.dealAttributeValues = dealAttributeValues;
    }

}
