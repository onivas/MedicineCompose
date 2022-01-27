package com.savinoordine.medicinecompose.domain.repository.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.savinoordine.medicinecompose.domain.repository.database.entity.MedicineEntity

@Dao
interface MedicineDao {

    @Query("SELECT * FROM MedicineEntity")
    fun fetchMedicines(): List<MedicineEntity>
}