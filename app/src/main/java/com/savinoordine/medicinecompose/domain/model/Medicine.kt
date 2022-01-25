package com.savinoordine.medicinecompose.domain.model

data class Medicine(
    val name: String,
    val shortDescription: String,
)

data class Medicines(
    val medicineList: List<Medicine>,
)