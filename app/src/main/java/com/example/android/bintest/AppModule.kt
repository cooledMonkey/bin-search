package com.example.android.bintest

import android.content.Context
import androidx.room.Room
import com.example.android.bintest.database.BinDatabase
import com.example.android.bintest.database.BinDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideBinDatabase(@ApplicationContext context: Context): BinDatabase {
        return Room.databaseBuilder(
            context = context,
            BinDatabase::class.java,
            "card_info_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideBinDAO(appDatabase: BinDatabase): BinDatabaseDao {
        return appDatabase.binDatabaseDao
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}
