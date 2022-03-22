package com.savinoordine.medicinecompose.domain.mapper

import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.database.entity.MedicineEntity

fun List<MedicineEntity>.toEntity(): List<Medicine> =
    map {
        Medicine(
            id = it.uid,
            name = it.name,
            shortDescription = it.description.orEmpty(),
            price = it.price.orEmpty(),
            isAtHome = it.isAtHome,
            expirationDate = it.expireDate
        )
    }


fun Medicine.toEntity(): MedicineEntity {
    return MedicineEntity(
        uid = id,
        name = name,
        description = shortDescription,
        price = price,
        isAtHome = isAtHome,
        expireDate = expirationDate
    )
}