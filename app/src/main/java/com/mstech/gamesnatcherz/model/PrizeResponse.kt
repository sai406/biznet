package com.mstech.gamesnatcherz.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PrizeResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: PrizeData? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class PrizeDetails(

	@field:SerializedName("PrizeMessage")
	val prizeMessage: String? = null,

	@field:SerializedName("PrizeNumber")
	val prizeNumber: Int? = null,

	@field:SerializedName("PrizePath")
	val prizePath: String? = null
) : Parcelable

@Parcelize
data class PrizeData(

	@field:SerializedName("GameDetails")
	val gameDetails: GameDetails? = null,

	@field:SerializedName("PrizeDetails")
	val prizeDetails: PrizeDetails? = null
) : Parcelable

@Parcelize
data class GamePrizes(

	@field:SerializedName("PrizeText")
	val prizeText: String? = null,

	@field:SerializedName("PrizeImage")
	val prizeImage: String? = null,

	@field:SerializedName("PrizeNumber")
	val prizeNumber: Int? = null
) : Parcelable

@Parcelize
data class GameDetails(

	@field:SerializedName("ThirdPrizeCount")
	val thirdPrizeCount: Int? = null,

	@field:SerializedName("ImageFile")
	val imageFile: String? = null,

	@field:SerializedName("Description")
	val description: String? = null,

	@field:SerializedName("ThirdPrizeImagePath")
	val thirdPrizeImagePath: String? = null,

	@field:SerializedName("FirstPrizeImagePath")
	val firstPrizeImagePath: String? = null,

	@field:SerializedName("IsActive")
	val isActive: Int? = null,

	@field:SerializedName("SecondPrizeCount")
	val secondPrizeCount: Int? = null,

	@field:SerializedName("ThirdPrizeImage")
	val thirdPrizeImage: String? = null,

	@field:SerializedName("ThirdPrizeWinCount")
	val thirdPrizeWinCount: Int? = null,

	@field:SerializedName("IntervalId")
	val intervalId: Int? = null,

	@field:SerializedName("Image")
	val image: String? = null,

	@field:SerializedName("SecondPrizeImageFile")
	val secondPrizeImageFile: String? = null,

	@field:SerializedName("ModifiedDate")
	val modifiedDate: String? = null,

	@field:SerializedName("Conditions")
	val conditions: String? = null,

	@field:SerializedName("FirstPrizeText")
	val firstPrizeText: String? = null,

	@field:SerializedName("StartDate")
	val startDate: String? = null,

	@field:SerializedName("FirstPrizeImage")
	val firstPrizeImage: String? = null,

	@field:SerializedName("ImagePath")
	val imagePath: String? = null,

	@field:SerializedName("TotalRecords")
	val totalRecords: Int? = null,

	@field:SerializedName("SecondPrizeImagePath")
	val secondPrizeImagePath: String? = null,

	@field:SerializedName("SecondPrizeWinCount")
	val secondPrizeWinCount: Int? = null,

	@field:SerializedName("BusinessId")
	val businessId: Int? = null,

	@field:SerializedName("SecondPrizeText")
	val secondPrizeText: String? = null,

	@field:SerializedName("FirstPrizeImageFile")
	val firstPrizeImageFile: String? = null,

	@field:SerializedName("FirstPrizeWinCount")
	val firstPrizeWinCount: Int? = null,

	@field:SerializedName("Title")
	val title: String? = null,

	@field:SerializedName("SwipeImagePath")
	val swipeImagePath: String? = null,

	@field:SerializedName("TotalPlayed")
	val totalPlayed: Int? = null,

	@field:SerializedName("OnceIn")
	val onceIn: Int? = null,

	@field:SerializedName("EndDate")
	val endDate: String? = null,

	@field:SerializedName("GamePrizes")
	val gamePrizes: List<GamePrizesItem?>? = null,

	@field:SerializedName("CreatedDateDisplay")
	val createdDateDisplay: String? = null,

	@field:SerializedName("SecondPrizeImage")
	val secondPrizeImage: String? = null,

	@field:SerializedName("StartDateDisplay")
	val startDateDisplay: String? = null,

	@field:SerializedName("FirstPrizeCount")
	val firstPrizeCount: Int? = null,

	@field:SerializedName("IsActiveText")
	val isActiveText: String? = null,

	@field:SerializedName("CreatedDate")
	val createdDate: String? = null,

	@field:SerializedName("ModifiedDateDisplay")
	val modifiedDateDisplay: String? = null,

	@field:SerializedName("ThirdPrizeText")
	val thirdPrizeText: String? = null,

	@field:SerializedName("EndDateDisplay")
	val endDateDisplay: String? = null,

	@field:SerializedName("ThirdPrizeImageFile")
	val thirdPrizeImageFile: String? = null,

	@field:SerializedName("GameId")
	val gameId: Int? = null,

	@field:SerializedName("Interval")
	val interval: String? = null
) : Parcelable
