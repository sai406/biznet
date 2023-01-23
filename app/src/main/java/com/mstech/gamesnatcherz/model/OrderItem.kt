package com.mstech.gamesnatcherz.model

data class OrderItem(
    val BusinessId: Int?,
    val CustomerId: Int?,
    val DealAttributeValues: String?,
    val DisplayPrice: String?,
    val IsAddressRequired: Int?,
    val OrderDate: String?,
    val OrderDateDisplay: String?,
    val OrderId: Int?,
    val OrderItemId: Int?,
    val Price: String?,
    val ProductId: Int?,
    val ProductImage: String?,
    val ProductImagePath: String?,
    val ProductName: String?,
    val Qty: Int?,
    val ShippingCost: Any?,
    val Status: Int?,
    val StatusDisplay: String?
)