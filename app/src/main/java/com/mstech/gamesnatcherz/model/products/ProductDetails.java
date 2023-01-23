
package com.mstech.gamesnatcherz.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** HARISH GADDAM */

public class ProductDetails {

    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductImage")
    @Expose
    private String productImage;
    @SerializedName("ProductImagePath")
    @Expose
    private String productImagePath;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("Displayprice")
    @Expose
    private String displayprice;
    @SerializedName("OfferPrice")
    @Expose
    private String offerPrice;
    @SerializedName("Displayofferprice")
    @Expose
    private String displayofferprice;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("StartDatestring")
    @Expose
    private String startDatestring;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("EndDatestring")
    @Expose
    private String endDatestring;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("QRCode")
    @Expose
    private String qRCode;
    @SerializedName("QRcodePath")
    @Expose
    private String qRcodePath;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedDatestring")
    @Expose
    private String createdDatestring;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("ModifiedDatestring")
    @Expose
    private String modifiedDatestring;
    @SerializedName("OrganisationId")
    @Expose
    private Integer organisationId;
    @SerializedName("Organisation")
    @Expose
    private String organisation;
    @SerializedName("CompanyNumber")
    @Expose
    private String companyNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("ContactName")
    @Expose
    private String contactName;
    @SerializedName("ContactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Logo")
    @Expose
    private String logo;
    @SerializedName("LogoPath")
    @Expose
    private String logoPath;
    @SerializedName("YoutubeLink")
    @Expose
    private String youtubeLink;
    @SerializedName("FacebookLink")
    @Expose
    private String facebookLink;
    @SerializedName("WebsiteLink")
    @Expose
    private String websiteLink;
    @SerializedName("IsApproved")
    @Expose
    private Integer isApproved;
    @SerializedName("TotalRecords")
    @Expose
    private Integer totalRecords;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDisplayprice() {
        return displayprice;
    }

    public void setDisplayprice(String displayprice) {
        this.displayprice = displayprice;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getDisplayofferprice() {
        return displayofferprice;
    }

    public void setDisplayofferprice(String displayofferprice) {
        this.displayofferprice = displayofferprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDatestring() {
        return startDatestring;
    }

    public void setStartDatestring(String startDatestring) {
        this.startDatestring = startDatestring;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDatestring() {
        return endDatestring;
    }

    public void setEndDatestring(String endDatestring) {
        this.endDatestring = endDatestring;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQRCode() {
        return qRCode;
    }

    public void setQRCode(String qRCode) {
        this.qRCode = qRCode;
    }

    public String getQRcodePath() {
        return qRcodePath;
    }

    public void setQRcodePath(String qRcodePath) {
        this.qRcodePath = qRcodePath;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDatestring() {
        return createdDatestring;
    }

    public void setCreatedDatestring(String createdDatestring) {
        this.createdDatestring = createdDatestring;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedDatestring() {
        return modifiedDatestring;
    }

    public void setModifiedDatestring(String modifiedDatestring) {
        this.modifiedDatestring = modifiedDatestring;
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

}
