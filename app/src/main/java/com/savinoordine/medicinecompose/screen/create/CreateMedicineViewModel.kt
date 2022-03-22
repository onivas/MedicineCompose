package com.savinoordine.medicinecompose.screen.create

import android.util.Log
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
                _uiState.value =
                    _uiState.value.copy(
                        closeNewMedicineView = true,
                        openDatePicker = false
                    )
            }
        }
    }

    fun onDescriptionChanged(value: String) {
        val medicine = _uiState.value.medicine.copy(shortDescription = value)
        _uiState.value = _uiState.value.copy(medicine = medicine, openDatePicker = false)
    }

    fun onNameChanged(value: String) {
        val medicine = _uiState.value.medicine.copy(name = value)
        _uiState.value = _uiState.value.copy(
            medicine = medicine,
            isSaveButtonEnable = medicine.isValid,
            openDatePicker = false
        )
        canMedicineBeSaved()
    }

    private fun canMedicineBeSaved() {
        if (_uiState.value.medicine.isValid) {
            _uiState.value = _uiState.value.copy(
                isSaveButtonEnable = true,
                openDatePicker = false
            )
        }
    }

    fun onPriceChanged(value: String) {
        val medicine = _uiState.value.medicine.copy(price = value)
        _uiState.value = _uiState.value.copy(
            medicine = medicine,
            openDatePicker = false
        )
    }

    fun onIsAtHomeChanged(isAtHome: Boolean) {
        val medicine = _uiState.value.medicine.copy(isAtHome = isAtHome)
        _uiState.value = _uiState.value.copy(
            medicine = medicine,
            openDatePicker = false
        )
    }

    fun onExpireDateChanged(expiringDate: String) {
        val medicine = _uiState.value.medicine.copy(expirationDate = expiringDate)
        _uiState.value = _uiState.value.copy(
            medicine = medicine,
            openDatePicker = false
        )
        Log.d(">>>", "time : $expiringDate")
    }

    fun onExpireDateClicked() {
        _uiState.value = _uiState.value.copy(openDatePicker = true)
    }
}

data class NewMedicineState(
    val medicine: Medicine = Medicine(),
    val isSaveButtonEnable: Boolean = false,
    val closeNewMedicineView: Boolean = false,
    val openDatePicker: Boolean = false,
    val error: String? = null,
)