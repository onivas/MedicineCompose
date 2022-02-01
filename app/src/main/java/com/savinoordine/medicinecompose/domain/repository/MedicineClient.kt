package com.savinoordine.medicinecompose.domain.repository

import com.savinoordine.medicinecompose.domain.mapper.toEntity
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.database.dao.MedicineDao
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject


class MedicineClient
@Inject
constructor(private val medicineDao: MedicineDao) : MedicineRepository {

    private var _medicines = MutableSharedFlow<List<Medicine>>(replay = 1)
    override val medicine: SharedFlow<List<Medicine>> = _medicines

    override suspend fun fetchMedicines() {
        val medicines = medicineDao.fetchMedicines()
            .map { it.toEntity() }

        _medicines.emit(medicines)
    }

    override suspend fun saveMedicine(medicine: Medicine) {
        return medicineDao.saveMedicine(medicine.toEntity())
    }
}