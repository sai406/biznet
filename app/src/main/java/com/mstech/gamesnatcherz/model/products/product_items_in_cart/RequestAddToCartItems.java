package com.mstech.gamesnatcherz.model.products.product_items_in_cart;

/** HARISH GADDAM */

public class RequestAddToCartItems {

    private int CartItemId;
    private int CartId;
    private int ProductId;
    private int BusinessId;
    private int CustomerId;
    private int Qty;
    private String Price;
    private String DealAttributeValues;
    private int Action;

    public RequestAddToCartItems(int cartItemId, int cartId, int productId, int businessId, int customerId, int qty, String price, String dealAttributeValues, int action) {
        CartItemId = cartItemId;
        CartId = cartId;
        ProductId = productId;
        BusinessId = businessId;
        CustomerId = customerId;
        Qty = qty;
        Price = price;
        DealAttributeValues = dealAttributeValues;
        Action = action;
    }

    public int getCartItemId() {
        return CartItemId;
    }

    public void setCartItemId(int cartItemId) {
        CartItemId = cartItemId;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(int businessId) {
        BusinessId = businessId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDealAttributeValues() {
        return DealAttributeValues;
    }

    public void setDealAttributeValues(String dealAttributeValues) {
        DealAttributeValues = dealAttributeValues;
    }

    public int getAction() {
        return Action;
    }

    public void setAction(int action) {
        Action = action;
    }
}
