
package com.mstech.gamesnatcherz.model.products.product_items_in_cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * HARISH GADDAM
 */

public class ResponseGetCartItem implements Serializable {

    @SerializedName("CartId")
    @Expose
    private Integer cartId;
    @SerializedName("MemberId")
    @Expose
    private Integer memberId;
    @SerializedName("BusinessId")
    @Expose
    private Integer businessId;
    @SerializedName("Organisation")
    @Expose
    private String organisation;
    @SerializedName("Logo")
    @Expose
    private String logo;
    @SerializedName("LogoPath")
    @Expose
    private String logoPath;
    @SerializedName("MemberName")
    @Expose
    private String memberName;
    @SerializedName("MemberEmail")
    @Expose
    private String memberEmail;
    @SerializedName("MemberMobile")
    @Expose
    private String memberMobile;
    @SerializedName("MemberAddress")
    @Expose
    private String memberAddress;
    @SerializedName("MemberPostalCode")
    @Expose
    private String memberPostalCode;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("PostalCode")
    @Expose
    private String postalCode;
    @SerializedName("TotalQty")
    @Expose
    private Integer totalQty;
    @SerializedName("TotalPrice")
    @Expose
    private String totalPrice;
    @SerializedName("DisplayTotalPrice")
    @Expose
    private String displayTotalPrice;
    @SerializedName("AddedDate")
    @Expose
    private String addedDate;
    @SerializedName("AddedDateDisplay")
    @Expose
    private String addedDateDisplay;
    @SerializedName("ShippingCharge")
    @Expose
    private String shippingCharge;
    @SerializedName("DisplayShippingCharge")
    @Expose
    private String displayShippingCharge;
    @SerializedName("CartItems")
    @Expose
    private ArrayList<CartItem> cartItems;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberPostalCode() {
        return memberPostalCode;
    }

    public void setMemberPostalCode(String memberPostalCode) {
        this.memberPostalCode = memberPostalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDisplayTotalPrice() {
        return displayTotalPrice;
    }

    public void setDisplayTotalPrice(String displayTotalPrice) {
        this.displayTotalPrice = displayTotalPrice;
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

    public String getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(String shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public String getDisplayShippingCharge() {
        return displayShippingCharge;
    }

    public void setDisplayShippingCharge(String displayShippingCharge) {
        this.displayShippingCharge = displayShippingCharge;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
