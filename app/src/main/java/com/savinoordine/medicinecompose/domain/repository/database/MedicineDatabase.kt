package com.savinoordine.medicinecompose.domain.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.savinoordine.medicinecompose.domain.repository.database.dao.MedicineDao
import com.savinoordine.medicinecompose.domain.repository.database.entity.MedicineEntity

@Database(entities = [MedicineEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}