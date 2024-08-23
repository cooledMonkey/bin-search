package com.example.android.bintest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import javax.inject.Inject
import javax.inject.Singleton

@Dao
interface BinDatabaseDao{
    @Insert
    fun insert(cardInfo: CardInfoEntity)

    @Query("SELECT * FROM card_info ORDER BY id DESC")
    fun getAllRequests(): LiveData<List<CardInfoEntity>>
}