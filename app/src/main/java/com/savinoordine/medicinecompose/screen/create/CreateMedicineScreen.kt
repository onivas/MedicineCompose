package com.savinoordine.medicinecompose.screen.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.screen.core.State

@Composable
fun CreateMedicineScreen(
    viewModel: CreateMedicineViewModel,
    navController: NavController
) {

    val uiState = viewModel.uiState
    var isButtonEnable = remember { false }
    var name = remember { "" }
    var description = remember { "" }

    when (uiState.state) {
        State.IDLE -> {
            isButtonEnable = uiState.isSaveButtonEnable
            name = uiState.medicine.name
            description = uiState.medicine.shortDescription
        }
        State.SUCCESS -> {
            LaunchedEffect(Unit) { navController.popBackStack() }
        }
    }

    MedicineForm(isButtonEnable,
        name,
        description,
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
    name: String,
    description: String,
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

            EditTextField(
                value = name,
                hint = "Name",
                onValueChanged = onNameChanged
            )

            EditTextField(
                value = description,
                hint = "Description",
                onValueChanged = onDescriptionChanged
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

@Composable
fun EditTextField(
    value: String,
    hint: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = hint) },
        onValueChange = { onValueChanged(it) },
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview
@Composable
fun PreviewNewMedicine() {
    MedicineForm(true, "", "", {}, {}) {}
}