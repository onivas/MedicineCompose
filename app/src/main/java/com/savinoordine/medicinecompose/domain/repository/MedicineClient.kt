package com.savinoordine.medicinecompose.domain.repository

import com.savinoordine.medicinecompose.domain.mapper.toEntity
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.database.dao.MedicineDao
import javax.inject.Inject


class MedicineClient
@Inject
constructor(private val medicineDao: MedicineDao) : MedicineRepository {

    override suspend fun fetchMedicines(): List<Medicine> {
        return medicineDao.fetchMedicines()
            .map { it.toEntity() }
    }

    override suspend fun saveMedicine(medicine: Medicine) {
        return medicineDao.saveMedicine(medicine.toEntity())
    }

}