package com.mstech.gamesnatcherz.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class ForgotPinResponse(
    @SerializedName("Id")
    val id: Int?,
    @SerializedName("Message")
    val message: String?,
    @SerializedName("Pin")
    val pin: String?
) : Parcelable