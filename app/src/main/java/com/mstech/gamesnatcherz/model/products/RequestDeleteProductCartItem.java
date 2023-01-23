package com.mstech.gamesnatcherz.model.products;

/** HARISH GADDAM */

public class RequestDeleteProductCartItem {

    private int CartId;
    private int OrganisationId;

    public RequestDeleteProductCartItem(int cartId, int organisationId) {
        CartId = cartId;
        OrganisationId = organisationId;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public int getOrganisationId() {
        return OrganisationId;
    }

    public void setOrganisationId(int organisationId) {
        OrganisationId = organisationId;
    }
}
