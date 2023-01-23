package com.mstech.gamesnatcherz.model.products;

/** HARISH GADDAM */

public class RequestGetProductReviews {

    private int ProductId;
    private int CustomerId;
    private int PageIndex;
    private int PageSize;

    public RequestGetProductReviews(int productId, int memberId, int pageIndex, int pageSize) {
        ProductId = productId;
        CustomerId = memberId;
        PageIndex = pageIndex;
        PageSize = pageSize;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getMemberId() {
        return CustomerId;
    }

    public void setMemberId(int memberId) {
        CustomerId = memberId;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }
}
