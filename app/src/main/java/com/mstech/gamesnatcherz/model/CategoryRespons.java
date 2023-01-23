package com.mstech.gamesnatcherz.model;

import com.google.gson.annotations.SerializedName;

public class CategoryRespons {

	@SerializedName("DealCatName")
	private String dealCatName;

	@SerializedName("ParentDealCatId")
	private String parentDealCatId;

	@SerializedName("CategoryImage")
	private String categoryImage;

	@SerializedName("DealCatId")
	private String dealCatId;

	public void setDealCatName(String dealCatName){
		this.dealCatName = dealCatName;
	}

	public String getDealCatName(){
		return dealCatName;
	}

	public void setParentDealCatId(String parentDealCatId){
		this.parentDealCatId = parentDealCatId;
	}

	public String getParentDealCatId(){
		return parentDealCatId;
	}

	public void setCategoryImage(String categoryImage){
		this.categoryImage = categoryImage;
	}

	public String getCategoryImage(){
		return categoryImage;
	}

	public void setDealCatId(String dealCatId){
		this.dealCatId = dealCatId;
	}

	public String getDealCatId(){
		return dealCatId;
	}
}