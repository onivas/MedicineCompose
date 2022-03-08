package com.savinoordine.medicinecompose.screen.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateMedicineViewModel
@Inject
constructor(var medicineRepository: MedicineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(value = NewMedicineState())
    val uiState: StateFlow<NewMedicineState> = _uiState

    fun saveMedicine() {
        if (_uiState.value.medicine.isValid) {
            viewModelScope.launch {
                medicineRepository.saveMedicine(_uiState.value.medicine)
                _uiState.value = _uiState.value.copy(closeNewMedicineView = true)
            }
        }
    }

    fun onDescriptionChanged(value: String) {
        val medicine = _uiState.value.medicine.copy(shortDescription = value)
       _uiState.value = _uiState.value.copy(medicine = medicine)
    }

    fun onNameChanged(value: String) {
        val medicine = _uiState.value.medicine.copy(name = value)
        _uiState.value = _uiState.value.copy(
            medicine = medicine,
            isSaveButtonEnable = medicine.isValid
        )
        canMedicineBeSaved()
    }

    private fun canMedicineBeSaved() {
        if (_uiState.value.medicine.isValid) {
            _uiState.value = _uiState.value.copy(
                isSaveButtonEnable = true
            )
        }
    }
}

data class NewMedicineState(
    val medicine: Medicine = Medicine(),
    val isSaveButtonEnable: Boolean = false,
    val closeNewMedicineView: Boolean = false,
    val error: String? = null,
)