package com.savinoordine.medicinecompose.screen.filter

import androidx.lifecycle.ViewModel
import com.savinoordine.medicinecompose.shared.FilterPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FilterViewModel
@Inject
constructor(private val filterPreference: FilterPreference) : ViewModel() {

    private val _state: MutableStateFlow<OnFilterApply> = MutableStateFlow(OnFilterApply())
    val state: StateFlow<OnFilterApply> = _state

    init {
        _state.value = OnFilterApply(filterPreference = filterPreference)
    }

    fun onFilterApplied() {
        _state.value = OnFilterApply(closeView = true)
    }

    fun isAtHomeClicked(value: Boolean) {
        filterPreference.isMedicineAtHome = value
    }

    fun priceSliderChanged(start: Float, end: Float) {
        filterPreference.minPrice = start.toInt()
        filterPreference.maxPrice = end.toInt()
    }

    fun isExpiredClicked(value: Boolean) {
        filterPreference.isMedicineExpired = value
    }

    fun onFilterCleaned() {
        filterPreference.clean()
        _state.value = OnFilterApply(filterPreference = filterPreference)
    }
}

data class OnFilterApply(
    val closeView: Boolean = false,
    val filterPreference: FilterPreference = FilterPreference()
)