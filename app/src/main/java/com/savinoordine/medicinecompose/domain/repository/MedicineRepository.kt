package com.savinoordine.medicinecompose.domain.repository

import com.savinoordine.medicinecompose.domain.model.Medicine

interface MedicineRepository {
    suspend fun fetchMedicines(): List<Medicine>
}