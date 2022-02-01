package com.savinoordine.medicinecompose.domain.repository

import com.savinoordine.medicinecompose.domain.model.Medicine
import kotlinx.coroutines.flow.SharedFlow

interface MedicineRepository {
    val medicine: SharedFlow<List<Medicine>>
    suspend fun fetchMedicines()
    suspend fun saveMedicine(medicine: Medicine)
}