package com.mstech.gamesnatcherz.model;

import java.io.Serializable;

public class Goodsmodel implements Serializable {
    private String dealname;
    private String merchantimage;
    private String dealimag;
    private String price;
    private String dealId;
    private Double sellingDealPrice;
    private String buyPrice;
    private Boolean IsQoinMerchant;

    public Boolean getQoinMerchant() {
        return IsQoinMerchant;
    }

    public void setQoinMerchant(Boolean qoinMerchant) {
        IsQoinMerchant = qoinMerchant;
    }

    public String getDigitalcoins() {
        return digitalcoins;
    }

    public void setDigitalcoins(String digitalcoins) {
        this.digitalcoins = digitalcoins;
    }

    public Double getSellingDealPrice() {
        return sellingDealPrice;
    }

    public void setSellingDealPrice(Double sellingDealPrice) {
        this.sellingDealPrice = sellingDealPrice;
    }

    private String digitalcoins;

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getFormatPrice() {
        return formatPrice;
    }

    public void setFormatPrice(String formatPrice) {
        this.formatPrice = formatPrice;
    }

    private String formatPrice;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    private String lat;

    public Goodsmodel(String lat, String lng, String isFavourite , String promotionalImages) {
        this.lat = lat;
        this.lng = lng;
        IsFavourite = isFavourite;
        PromotionalImages = promotionalImages;
    }

    private String lng;


    public String getPromotionalImages() {
        return PromotionalImages;
    }

    public void setPromotionalImages(String promotionalImages) {
        PromotionalImages = promotionalImages;
    }

    private String PromotionalImages;

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }


    private String dealType;


    public String getDealname() {
        return dealname;
    }

    public void setDealname(String dealname) {
        this.dealname = dealname;
    }

    public String getMerchantimage() {
        return merchantimage;
    }

    public void setMerchantimage(String merchantimage) {
        this.merchantimage = merchantimage;
    }

    public String getDealimag() {
        return dealimag;
    }

    public void setDealimag(String dealimag) {
        this.dealimag = dealimag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getBussinessName() {
        return bussinessName;
    }

    public void setBussinessName(String bussinessName) {
        this.bussinessName = bussinessName;
    }

    public String getBussinessaddress() {
        return bussinessaddress;
    }

    public void setBussinessaddress(String bussinessaddress) {
        this.bussinessaddress = bussinessaddress;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String merchantId;
    private String bussinessName;
    private String bussinessaddress;
    private String discription;
    private String condition;
    private String url;

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    private String destinationUrl;


    private String IsFavourite;

    public String getIsFavourite() {
        return IsFavourite;
    }

    public void setIsFavourite(String isFavourite) {
        IsFavourite = isFavourite;
    }
    public String getDealprice() {
        return dealprice;
    }

    public void setDealprice(String dealprice) {
        this.dealprice = dealprice;
    }

    private String dealprice;


    public String getAttributename() {
        return attributename;
    }

    public void setAttributename(String attributename) {
        this.attributename = attributename;
    }

    private String attributename;

    public Goodsmodel() {
        this.dealname = dealname;
        this.merchantimage = merchantimage;
        this.dealimag = dealimag;
        this.price = price;
        this.dealprice = dealprice;
        this.dealId = dealId;
        this.merchantId = merchantId;
        this.bussinessName = bussinessName;
        this.bussinessaddress = bussinessaddress;
        this.discription = discription;
        this.condition = condition;
        this.url = url;
        this.dealType = dealType;
        this.attributename = attributename;
        this.destinationUrl = destinationUrl;

    }


}
