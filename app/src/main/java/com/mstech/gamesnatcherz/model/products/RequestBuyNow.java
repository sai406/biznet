package com.mstech.gamesnatcherz.model.products;

/** HARISH GADDAM */

public class RequestBuyNow {

    private int CartId;
    private String PostalCode;
    private String Address;
    private String ShippingCharge;

    public RequestBuyNow(int cartId , String postalCode, String address , String shippingCharge) {
        CartId = cartId ;PostalCode =postalCode; Address=address;ShippingCharge =shippingCharge;
    }


    public int getCartId() {
        return CartId;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getShippingCharge() {
        return ShippingCharge;
    }

    public void setShippingCharge(String shippingCharge) {
        ShippingCharge = shippingCharge;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }
}
