package com.savinoordine.medicinecompose.screen.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.screen.core.State

@Composable
fun NewMedicineScreen(
    viewModel: NewMedicineViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState = viewModel.uiState
    var isButtonEnable = false

    when (uiState.state) {
        State.IDLE -> {
            isButtonEnable = uiState.isSaveButtonEnable
        }
        State.SUCCESS -> {
            navController.navigateUp()
        }
    }

    MedicineForm(isButtonEnable,
        {
            viewModel.onNameChanged(it)
        }, {
            viewModel.onDescriptionChanged(it)
        }) {
        viewModel.saveMedicine()
    }
}


@Composable
fun MedicineForm(
    isButtonEnable: Boolean,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onSaveMedicineClick: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = "",
                label = { Text(text = "Name") },
                onValueChange = { onNameChanged(it) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = "",
                label = { Text(text = "Description") },
                onValueChange = { onDescriptionChanged(it) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
                enabled = isButtonEnable,
                onClick = { onSaveMedicineClick() }) {
                Text(text = "Save")
            }
        }
    }
}

@Preview
@Composable
fun PreviewNewMedicine() {
    MedicineForm(true, {}, {}) {}
}