package com.savinoordine.medicinecompose.domain.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.savinoordine.medicinecompose.domain.repository.database.entity.MedicineEntity

@Dao
interface MedicineDao {

    @Query("SELECT * FROM MedicineEntity")
    suspend fun fetchMedicines(): List<MedicineEntity>  // TODO use livedata

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMedicine(medicineEntity: MedicineEntity)
}