package com.savinoordine.medicinecompose.screen.create

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.screen.core.TopBar

@Composable
fun CreateMedicineScreen(
    viewModel: CreateMedicineViewModel,
    navController: NavController
) {

    Crossfade(targetState = viewModel.uiState.collectAsState()) { state ->
        if (state.value.closeNewMedicineView) LaunchedEffect(Unit) { navController.popBackStack() }
        MedicineForm(
            isButtonEnable = state.value.isSaveButtonEnable,
            name = state.value.medicine.name,
            description = state.value.medicine.shortDescription,
            onNameChanged = { viewModel.onNameChanged(it) },
            onDescriptionChanged = { viewModel.onDescriptionChanged(it) },
        ) {
            viewModel.saveMedicine()
        }
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
    Scaffold(
        topBar = { TopBar(title = "Add new medicine", onBackToListClicked = null) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column() {
                EditTextField(
                    value = name,
                    hint = "Medicine name",
                    onValueChanged = { onNameChanged(it) }
                )

                EditTextField(
                    value = description,
                    hint = "Description",
                    onValueChanged = onDescriptionChanged
                )
            }
            Column() {
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(top = 8.dp),
                    enabled = isButtonEnable,
                    onClick = { onSaveMedicineClick() }) {
                    Text(text = "Save")
                }
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