package com.savinoordine.medicinecompose.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.route.NEW_MEDICINE_ROUTE

@Composable
fun MedicineList(
    viewModel: MedicineListViewModel,
    navController: NavController
) {


    val medicines = viewModel.uiState.medicines
    ShowMedicines(medicines) {
        navController.navigate(NEW_MEDICINE_ROUTE)
    }


//    val uiState = viewModel.uiState

//    when (uiState.state) {
//        State.IDLE -> {
//            val medicines = uiState.medicines
//            ShowMedicines(medicines) {
//                navController.navigate(NEW_MEDICINE_ROUTE)
//            }
//        }
//        State.LOADING -> {
//            showLoading()
//        }
//        State.ERROR -> {
//            uiState.error?.let { ShowError(it) }
//        }
//        State.SUCCESS -> {}
//    }
}

@Composable
fun ShowError(error: String) {

}

@Composable
fun showLoading() {

}

@Composable
fun ShowMedicines(
    medicines: List<Medicine>,
    addNewMedicineClick: () -> Unit
) {

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
                .padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items = medicines) { medicine ->
                MedicineItem(medicine)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MedicineItem(medicine: Medicine) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 4.dp,
        onClick = {

        }
    ) {
        Column(
            modifier = Modifier
                .background(Color.Green)
                .padding(4.dp),
        ) {
            Text(text = medicine.name)
            Text(text = medicine.shortDescription)
        }
    }
}

@Preview
@Composable
fun PreviewList() {
    ShowMedicines(medicines = listOf(Medicine("name", "description"))) {}
}