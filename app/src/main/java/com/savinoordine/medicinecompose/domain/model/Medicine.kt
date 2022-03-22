package com.savinoordine.medicinecompose.domain.model


sealed class Pharma

data class Medicine(
    val id: Int = 0,
    val name: String = "",
    val shortDescription: String = "",
    val price: String = "",
    val isAtHome: Boolean = false,
    val expirationDate: String? = null,
) : Pharma() {
    val isValid: Boolean = name.trim().isNotEmpty()
}

