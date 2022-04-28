package com.savinoordine.medicinecompose.shared

import com.savinoordine.medicinecompose.screen.filter.MAX_PRICE
import com.savinoordine.medicinecompose.screen.filter.MIN_PRICE
import javax.inject.Inject

data class FilterPreference(
    var isMedicineAtHome: Boolean = false,
    var isMedicineExpired: Boolean = false,
    var minPrice: Int = MIN_PRICE.toInt(),
    var maxPrice: Int = MAX_PRICE.toInt(),
) {
    fun clean() {
        isMedicineAtHome = false
        isMedicineExpired = false
        minPrice = MIN_PRICE.toInt()
        maxPrice = MAX_PRICE.toInt()
    }
}
