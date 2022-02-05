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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineListViewModel
@Inject
constructor(private val medicineRepository: MedicineRepository) : ViewModel() {

    var uiState by mutableStateOf(value = MedicineListState(state = State.LOADING))
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            medicineRepository.fetchMedicines()
                .distinctUntilChanged()
                .collect { medicines ->
                    uiState = uiState.copy(
                        state = State.IDLE,
                        medicines = if (medicines.isNullOrEmpty()) emptyList() else medicines
                    )
                }
        }
    }

//    fun fetchMedicines() {
//        viewModelScope.launch {
//            uiState = uiState.copy(state = State.LOADING)
//            medicineRepository.fetchMedicines()
//        }
//    }
}

data class MedicineListState(
    override val state: State = State.IDLE,
    val medicines: List<Medicine> = emptyList(),
    val error: String? = null,
) : ScreenState