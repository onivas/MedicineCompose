package com.savinoordine.medicinecompose.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.MedicineRepository
import com.savinoordine.medicinecompose.shared.FilterPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineListViewModel
@Inject
constructor(
    private val medicineRepository: MedicineRepository,
    private val filterPreference: FilterPreference,
) : ViewModel() {

    private var _uiState = MutableStateFlow(MedicineListState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    fun fetchMedicines() {
        viewModelScope.launch(Dispatchers.IO) {
            medicineRepository.fetchMedicines()
                .distinctUntilChanged()
                .collect { medicines ->
                    _uiState.value = _uiState.value.copy(
                        medicines = if (medicines.isNullOrEmpty()) emptyList() else medicines,
                        selectedMedicine = null
                    )
                }
        }
    }

    fun deleteMedicine(medicine: Medicine) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            medicineRepository.deleteMedicine(medicine)
        }
    }

    fun selectMedicine(medicine: Medicine) {
        _uiState.value = _uiState.value.copy(selectedMedicine = medicine)
    }

    fun removeSelectedMedicine() {
        _uiState.value = _uiState.value.copy(selectedMedicine = null)
    }
}

data class MedicineListState(
    val medicines: List<Medicine> = emptyList(),
    val selectedMedicine: Medicine? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)