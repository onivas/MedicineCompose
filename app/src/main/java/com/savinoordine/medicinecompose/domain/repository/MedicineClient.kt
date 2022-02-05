package com.savinoordine.medicinecompose.domain.repository

import com.savinoordine.medicinecompose.domain.mapper.toEntity
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.database.dao.MedicineDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MedicineClient
@Inject
constructor(private val medicineDao: MedicineDao) : MedicineRepository {

    override fun fetchMedicines(): Flow<List<Medicine>> {
        return medicineDao.fetchMedicines()
            .map { it.toEntity() }
    }

    override suspend fun saveMedicine(medicine: Medicine) {
        return medicineDao.saveMedicine(medicine.toEntity())
    }
}