package com.savinoordine.medicinecompose.domain.mapper

import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.database.entity.MedicineEntity

fun MedicineEntity.toEntity(): Medicine {
    return Medicine(
        name = name,
        shortDescription = description.orEmpty()
    )
}