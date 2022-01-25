package com.savinoordine.medicinecompose.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.savinoordine.medicinecompose.domain.model.Medicines
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MedicineListViewModel
@Inject
constructor() : ViewModel() {

    var uiState by mutableStateOf(value = MedicineListState())
        private set

    private fun fetchMedicines() {

    }

}

data class MedicineListState(
    val medicines: Medicines? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)