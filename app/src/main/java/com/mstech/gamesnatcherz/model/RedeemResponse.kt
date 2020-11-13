package com.mstech.gamesnatcherz.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RedeemResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: RedeemData? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class RedeemData(

	@field:SerializedName("Message")
	val message: String? = null,

	@field:SerializedName("Id")
	val id: Int? = null
) : Parcelable
