package com.kobylynskyiv.data.di

import android.content.Context
import androidx.room.Room
import com.kobylynskyiv.data.local.AppDatabase
import com.kobylynskyiv.data.local.dao.FruitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalProvides {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "db-example"
        )
            .build()
    }

    @Provides
    fun provideFruitDao(appDatabase: AppDatabase): FruitDao = appDatabase.getFruitDao()

}