package com.savinoordine.medicinecompose.domain.model

data class Medicine(
    val name: String = "",
    val shortDescription: String? = null,
) {
    val isValid: Boolean = name.isNotEmpty()
}
