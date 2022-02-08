package com.savinoordine.medicinecompose.domain.repository.database.dao

import androidx.room.*
import com.savinoordine.medicinecompose.domain.repository.database.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Query("SELECT * FROM MedicineEntity")
    fun fetchMedicines(): Flow<List<MedicineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMedicine(medicineEntity: MedicineEntity)

    @Delete
    suspend fun deleteMedicine(medicine: MedicineEntity)
}