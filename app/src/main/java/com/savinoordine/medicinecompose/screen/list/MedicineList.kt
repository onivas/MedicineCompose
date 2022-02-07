package com.savinoordine.medicinecompose.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.route.NEW_MEDICINE_ROUTE
import com.savinoordine.medicinecompose.screen.core.State

@Composable
fun MedicineList(
    viewModel: MedicineListViewModel,
    navController: NavController
) {

    LaunchedEffect(Unit) { viewModel.fetchMedicines() }

    val uiState = viewModel.uiState

    when (uiState.state) {
        State.IDLE -> {
            val medicines = uiState.medicines
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
fun ShowMedicines(
    medicines: List<Medicine>,
    addNewMedicineClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                content = { Text(text = "+") },
                onClick = { addNewMedicineClick() })
        },
    ) {
        if (medicines.isEmpty()) {
            ShowEmptyListPage()
        } else {
            ShowMedicinesListPage(medicines)
        }
    }
}

@Composable
fun ShowEmptyListPage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            color = Color.Magenta,
            text = "Click + to add medicine"
        )
    }
}

@Composable
fun ShowMedicinesListPage(medicines: List<Medicine>) {
    val listState = rememberLazyListState()
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