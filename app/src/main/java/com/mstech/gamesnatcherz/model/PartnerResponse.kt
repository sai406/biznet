package com.mstech.gamesnatcherz.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PartnerResponse(

	@field:SerializedName("Email")
	val email: String? = null,

	@field:SerializedName("PrizeCount")
	val prizeCount: Int? = null,

	@field:SerializedName("Address")
	val address: String? = null,

	@field:SerializedName("IsActive")
	val isActive: Int? = null,

	@field:SerializedName("QRPath")
	val qRPath: String? = null,

	@field:SerializedName("BusinessType")
	val businessType: String? = null,

	@field:SerializedName("LogoFile")
	val logoFile: String? = null,

	@field:SerializedName("ModifiedDate")
	val modifiedDate: String? = null,

	@field:SerializedName("BusinessLogoPath")
	val businessLogoPath: String? = null,

	@field:SerializedName("ClaimedDateDisplay")
	val claimedDateDisplay: String? = null,

	@field:SerializedName("PrizeImage")
	val prizeImage: String? = null,

	@field:SerializedName("TotalRecords")
	val totalRecords: Int? = null,

	@field:SerializedName("PrizeImagePath")
	val prizeImagePath: String? = null,

	@field:SerializedName("BusinessId")
	val businessId: Int? = null,

	@field:SerializedName("Password")
	val password: String? = null,

	@field:SerializedName("AdminId")
	val adminId: Int? = null,

	@field:SerializedName("QR")
	val qR: String? = null,

	@field:SerializedName("LogoPath")
	val logoPath: String? = null,

	@field:SerializedName("UserName")
	val userName: String? = null,

	@field:SerializedName("BusinessName")
	val businessName: String? = null,

	@field:SerializedName("FirstName")
	val firstName: String? = null,

	@field:SerializedName("PrizeImageFile")
	val prizeImageFile: String? = null,

	@field:SerializedName("ClaimedDate")
	val claimedDate: String? = null,

	@field:SerializedName("Mobile")
	val mobile: String? = null,

	@field:SerializedName("CreatedDateDisplay")
	val createdDateDisplay: String? = null,

	@field:SerializedName("Logo")
	val logo: String? = null,

	@field:SerializedName("BusinessTypeId")
	val businessTypeId: Int? = null,

	@field:SerializedName("FullName")
	val fullName: String? = null,

	@field:SerializedName("IsActiveText")
	val isActiveText: String? = null,

	@field:SerializedName("CreatedDate")
	val createdDate: String? = null,

	@field:SerializedName("ModifiedDateDisplay")
	val modifiedDateDisplay: String? = null,

	@field:SerializedName("LastName")
	val lastName: String? = null
) : Parcelable
