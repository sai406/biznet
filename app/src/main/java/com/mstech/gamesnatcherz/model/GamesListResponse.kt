package com.mstech.gamesnatcherz.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GamesListResponse(

	@field:SerializedName("Status")
	val status: Boolean? = null,

	@field:SerializedName("Data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("Success")
	val success: Boolean? = null
) : Parcelable

@Parcelize
data class GamePrizesItem(

	@field:SerializedName("PrizeText")
	val prizeText: String? = null,

	@field:SerializedName("PrizeImage")
	val prizeImage: String? = null,

	@field:SerializedName("PrizeNumber")
	val prizeNumber: Int? = null
) : Parcelable

@Parcelize
data class DataItem(

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

    @field:SerializedName("Finish")
	val finish: Int? = null,

	@field:SerializedName("FinishedDisplay")
	val finishedDisplay: String? = null,

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
	val interval: String? = null,
	@field:SerializedName("BusinessName")
	val businessName: String? = null,
	@field:SerializedName("BusinessLogoPath")
	val businessLogoPath: String? = null
) : Parcelable
