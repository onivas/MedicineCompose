package com.savinoordine.medicinecompose.screen.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.repository.MedicineRepository
import com.savinoordine.medicinecompose.screen.core.ScreenState
import com.savinoordine.medicinecompose.screen.core.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateMedicineViewModel
@Inject
constructor(var medicineRepository: MedicineRepository) : ViewModel() {

    var uiState by mutableStateOf(value = NewMedicineState())
        private set

    fun saveMedicine() {
        if (uiState.medicine.isValid) {
            uiState = uiState.copy(state = State.LOADING)
            viewModelScope.launch {
                medicineRepository.saveMedicine(uiState.medicine)
                uiState = uiState.copy(state = State.SUCCESS)
            }
        }
    }

    fun onDescriptionChanged(value: String) {
        val medicine = uiState.medicine.copy(shortDescription = value)
        uiState = uiState.copy(medicine = medicine)
    }

    fun onNameChanged(value: String) {
        val medicine = uiState.medicine.copy(name = value)
        uiState = uiState.copy(
            medicine = medicine,
            isSaveButtonEnable = medicine.isValid
        )
        canMedicineBeSaved()
    }

    private fun canMedicineBeSaved() {
        if (uiState.medicine.isValid) {
            uiState = uiState.copy(
                state = State.IDLE,
                isSaveButtonEnable = true
            )
        }
    }
}

data class NewMedicineState(
    override val state: State = State.IDLE,
    val medicine: Medicine = Medicine(),
    val isSaveButtonEnable: Boolean = false,
    val error: String? = null,
) : ScreenState