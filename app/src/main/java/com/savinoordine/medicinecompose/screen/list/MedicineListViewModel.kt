package com.savinoordine.medicinecompose.screen.list

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
class MedicineListViewModel
@Inject
constructor(private val medicineRepository: MedicineRepository) : ViewModel() {

    var uiState by mutableStateOf(value = MedicineListState())
        private set

    private fun fetchMedicines() {
        uiState = uiState.copy(state = State.LOADING)
        viewModelScope.launch {
            val medicines = medicineRepository.fetchMedicines()
            uiState = uiState.copy(
                state = State.IDLE,
                medicines = medicines
            )
        }
    }

}

data class MedicineListState(
    override val state: State = State.IDLE,
    val medicines: List<Medicine>? = null,
    val error: String? = null,
) : ScreenState