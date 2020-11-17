package com.mstech.gamesnatcherz.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class RedeemPrizeList(

    @SerializedName("GameConditions")
    val gameConditions: String?,
    @SerializedName("PrizeImagePath")
    val prizeImagePath: String?,
    @SerializedName("PrizeText")
    val prizeText: String?,
    @SerializedName("RedeemCode")
    val redeemCode: String?,
    @SerializedName("ResultId")
    val resultId: Int?,
    @SerializedName("Shared")
    val shared: Int?,
    @SerializedName("SharedFrom")
    val sharedFrom: String?,
    @SerializedName("Type")
    val type: Int?

) : Parcelable