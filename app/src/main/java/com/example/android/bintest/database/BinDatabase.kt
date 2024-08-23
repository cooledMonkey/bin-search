package com.example.android.bintest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardInfoEntity::class], version = 1, exportSchema = false)
abstract class BinDatabase : RoomDatabase() {


    abstract val binDatabaseDao: BinDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: BinDatabase? = null

        fun getInstance(context: Context): BinDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BinDatabase::class.java,
                        "card_info_database"
                    ) .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
