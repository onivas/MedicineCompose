package com.savinoordine.medicinecompose.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.screen.core.State
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.route.NEW_MEDICINE_ROUTE

@Composable
fun MedicineList(
    viewModel: MedicineListViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState = viewModel.uiState

    when (uiState.state) {
        State.IDLE -> {
            val medicines = uiState.medicines ?: emptyList()
            ShowMedicines(medicines) {
                navController.navigate(NEW_MEDICINE_ROUTE)
            }
        }
        State.LOADING -> {
            showLoading()
        }
        State.ERROR -> {
            uiState.error?.let { ShowError(it) }
        }
        State.SUCCESS -> {}
    }
}

@Composable
fun ShowError(error: String) {

}

@Composable
fun showLoading() {

}

@Composable
fun ShowMedicines(medicines: List<Medicine>, addNewMedicineClick: () -> Unit) {

    val listState = rememberLazyListState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                content = { Text(text = "+") },
                onClick = { addNewMedicineClick() })
        },
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Green)
                .padding(8.dp),
        ) {
            items(items = medicines) { medicine ->
                MedicineItem(medicine)
            }
        }
    }


}

@Composable
fun MedicineItem(medicine: Medicine) {
    Text(text = medicine.name)
    Text(text = medicine.shortDescription.orEmpty())
}

@Preview
@Composable
fun PreviewList() {
    ShowMedicines(medicines = listOf(Medicine("name", "description"))) {}
}