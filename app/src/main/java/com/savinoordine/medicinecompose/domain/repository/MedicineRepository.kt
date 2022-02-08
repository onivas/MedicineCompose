package com.savinoordine.medicinecompose.domain.repository

import com.savinoordine.medicinecompose.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineRepository {
    fun fetchMedicines(): Flow<List<Medicine>>
    suspend fun saveMedicine(medicine: Medicine)
    suspend fun deleteMedicine(medicine: Medicine)
}