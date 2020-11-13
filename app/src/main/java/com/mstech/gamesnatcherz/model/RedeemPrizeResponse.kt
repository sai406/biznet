package com.mstech.gamesnatcherz.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedeemPrizeResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: List<PrizeDataItem?>? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class PrizeDataItem(

	@field:SerializedName("Status")
	val status: Int? = null,

	@field:SerializedName("PrizeText")
	val prizeText: String? = null,

	@field:SerializedName("RedeemCode")
	val redeemCode: String? = null,

	@field:SerializedName("BusinessName")
	val businessName: String? = null,

	@field:SerializedName("UpdatedDate")
	val updatedDate: String? = null,

	@field:SerializedName("StatusText")
	val statusText: String? = null,

	@field:SerializedName("SharedTo")
	val sharedTo: String? = null,

	@field:SerializedName("ZipCode")
	val zipCode: String? = null,

	@field:SerializedName("CustomerId")
	val customerId: Int? = null,

	@field:SerializedName("Shared")
	val shared: Int? = null,

	@field:SerializedName("ResultId")
	val resultId: Int? = null,

	@field:SerializedName("BusinessAddress")
	val businessAddress: String? = null,

	@field:SerializedName("PromotionChecked")
	val promotionChecked: Int? = null,

	@field:SerializedName("CreatedDateString")
	val createdDateString: String? = null,

	@field:SerializedName("UpdatedDateString")
	val updatedDateString: String? = null,

	@field:SerializedName("GameConditions")
	val gameConditions: String? = null,

	@field:SerializedName("CreatedDate")
	val createdDate: String? = null,

	@field:SerializedName("PrizeImage")
	val prizeImage: String? = null,

	@field:SerializedName("PrizeImagePath")
	val prizeImagePath: String? = null,

	@field:SerializedName("PrizeNumber")
	val prizeNumber: Int? = null,

	@field:SerializedName("BusinessId")
	val businessId: Int? = null,

	@field:SerializedName("SharedFrom")
	val sharedFrom: String? = null,

	@field:SerializedName("GameId")
	val gameId: Int? = null
) : Parcelable
