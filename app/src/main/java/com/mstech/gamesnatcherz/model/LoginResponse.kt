package com.mstech.gamesnatcherz.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: profileData? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
)

data class profileData(

	@field:SerializedName("Email")
	val email: String? = null,

	@field:SerializedName("Address")
	val address: String? = null,

	@field:SerializedName("FirstName")
	val firstName: String? = null,

	@field:SerializedName("ZipCode")
	val zipCode: String? = null,

	@field:SerializedName("Genderstring")
	val genderstring: String? = null,

	@field:SerializedName("CustomerId")
	val customerId: Int? = null,

	@field:SerializedName("Gender")
	val gender: Int? = null,

	@field:SerializedName("Mobile")
	val mobile: String? = null,

	@field:SerializedName("Agestring")
	val agestring: String? = null,

	@field:SerializedName("Pin")
	val pin: String? = null,

	@field:SerializedName("CreatedDate")
	val createdDate: String? = null,

	@field:SerializedName("TotalRecords")
	val totalRecords: Int? = null,

	@field:SerializedName("LastName")
	val lastName: String? = null,

	@field:SerializedName("Age")
	val age: Int? = null,

	@field:SerializedName("BusinessId")
	val businessId: Int? = null
)
