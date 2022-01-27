package com.savinoordine.medicinecompose.di

import android.content.Context
import androidx.room.Room
import com.savinoordine.medicinecompose.domain.repository.database.AppDatabase
import com.savinoordine.medicinecompose.domain.repository.database.dao.MedicineDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "MedicineDatabase"
        ).build()
    }

    @Provides
    fun provideMedicineDao(database: AppDatabase): MedicineDao {
        return database.medicineDao()
    }
}