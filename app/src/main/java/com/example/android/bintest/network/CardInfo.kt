package com.example.android.bintest.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfo(
    val number: NumberInfo? = NumberInfo(),
    val scheme: String = "",
    val type: String = "",
    val brand: String = "",
    val prepaid: Boolean = false,
    var country: CountryInfo = CountryInfo(),
    val bank: BankInfo = BankInfo()
): Parcelable

@Parcelize
data class NumberInfo(
    val length: Int = 0,
    val luhn: Boolean = false
): Parcelable

@Parcelize
data class CountryInfo(
    val numeric: Int = 0,
    val alpha: String = "",
    var name: String = "",
    val emoji: String = "",
    val currency: String = "",
    val latitude: String = "",
    val longitude: String = ""
): Parcelable

@Parcelize
data class BankInfo(
    val name: String = "", var url: String = "", val phone: String = "", val city: String = ""
): Parcelable

