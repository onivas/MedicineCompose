package com.savinoordine.medicinecompose.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.domain.model.Medicine

@Composable
fun MedicineList(
    viewModel: MedicineListViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState = viewModel.uiState

    if (uiState.isLoading) {
        showLoading()
    } else {
        uiState.medicines?.let { ShowMedicines(it.medicineList) }
        uiState.error?.let { ShowError(it) }
    }

}

@Composable
fun ShowError(error: String) {

}

@Composable
fun showLoading() {

}

@Composable
fun ShowMedicines(medicines: List<Medicine>) {

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
    ) {
        items(items = medicines) { medicine ->
            MedicineItem(medicine)
        }
    }
}

@Composable
fun MedicineItem(medicine: Medicine) {
    Text(text = medicine.name)
    Text(text = medicine.shortDescription)
}

@Preview
@Composable
fun PreviewList() {
    ShowMedicines(medicines = listOf(Medicine("name", "description")))
}