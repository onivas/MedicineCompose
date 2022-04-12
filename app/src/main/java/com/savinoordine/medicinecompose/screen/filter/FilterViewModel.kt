package com.savinoordine.medicinecompose.screen.filter

import androidx.lifecycle.ViewModel
import com.savinoordine.medicinecompose.shared.FilterPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class FilterViewModel
@Inject
constructor(var filterPreference: FilterPreference) : ViewModel() {

    private val _state: MutableStateFlow<OnFilterApply> = MutableStateFlow(OnFilterApply())
    val state: StateFlow<OnFilterApply> = _state

    fun onFilterApplied() {
        _state.value = OnFilterApply(closeView = true)
    }

    fun isAtHomeClicked(value: Boolean) {
        filterPreference = filterPreference.copy(isMedicineAtHome = value)
    }

    fun priceSliderChanged(start: Float, end: Float) {
        filterPreference = filterPreference.copy(minPrice = start.toInt(), maxPrice = end.toInt())
    }

    fun isExpiredClicked(value: Boolean) {
        filterPreference = filterPreference.copy(isMedicineExpired = value)
    }
}

data class OnFilterApply(val closeView: Boolean = false)