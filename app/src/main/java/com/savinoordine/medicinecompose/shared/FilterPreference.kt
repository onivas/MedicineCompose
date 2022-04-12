package com.savinoordine.medicinecompose.shared

import com.savinoordine.medicinecompose.screen.filter.MAX_PRICE
import com.savinoordine.medicinecompose.screen.filter.MIN_PRICE
import javax.inject.Inject

data class FilterPreference(
    val isMedicineAtHome: Boolean = false,
    val isMedicineExpired: Boolean = false,
    val minPrice: Int = MIN_PRICE.toInt(),
    val maxPrice: Int = MAX_PRICE.toInt(),
)
