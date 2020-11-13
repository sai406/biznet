package com.mstech.gamesnatcherz.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: Data? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class Data(

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
) : Parcelable
