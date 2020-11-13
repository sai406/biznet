package com.mstech.gamesnatcherz.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: SignupData? = null
)

data class SignupData(

	@field:SerializedName("Message")
	val message: Any? = null,

	@field:SerializedName("Id")
	val id: Int? = null
)
