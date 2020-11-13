package com.mstech.gamesnatcherz.model

import com.google.gson.annotations.SerializedName

data class PrizeOptionsResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: OptionsData? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
)

data class OptionsItem(

	@field:SerializedName("CreatedDateString")
	val createdDateString: String? = null,

	@field:SerializedName("OptionText")
	val optionText: String? = null,

	@field:SerializedName("ExpiryDate")
	val expiryDate: String? = null,

	@field:SerializedName("Description")
	val description: String? = null,

	@field:SerializedName("TermsAndConditions")
	val termsAndConditions: String? = null,

	@field:SerializedName("PrizeId")
	val prizeId: Int? = null,

	@field:SerializedName("IsActive")
	val isActive: Int? = null,

	@field:SerializedName("CreatedDate")
	val createdDate: String? = null,

	@field:SerializedName("ValidFrom")
	val validFrom: String? = null,

	@field:SerializedName("ValidFromString")
	val validFromString: String? = null,

	@field:SerializedName("ExpiryDateString")
	val expiryDateString: String? = null,

	@field:SerializedName("GPOId")
	val gPOId: Int? = null
)

data class OptionsData(

	@field:SerializedName("Options")
	val options: List<OptionsItem?>? = null,

	@field:SerializedName("TermsAndConditions")
	val termsAndConditions: String? = null,

	@field:SerializedName("PrizeId")
	val prizeId: Int? = null,

	@field:SerializedName("PrizeImage")
	val prizeImage: String? = null,

	@field:SerializedName("PrizeImagePath")
	val prizeImagePath: String? = null,

	@field:SerializedName("DisplayMessage")
	val displayMessage: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
)
