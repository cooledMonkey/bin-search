package com.example.android.bintest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.bintest.R

@Entity(tableName = "card_info")
data class CardInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo
    var number: String = "",
    @ColumnInfo
    var country: String? = "",
    @ColumnInfo
    var type: String?= "",
    @ColumnInfo
    var coordinates: String?= "",
    @ColumnInfo(name = "bank_city")
    var bankCity: String?= "",
    @ColumnInfo(name = "bank_url")
    var bankUrl: String?= "",
    @ColumnInfo(name = "bank_phone")
    var bankPhone: String?= ""
    )
