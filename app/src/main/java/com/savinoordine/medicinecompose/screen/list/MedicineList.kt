package com.savinoordine.medicinecompose.screen.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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

@ExperimentalFoundationApi
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
            ShowMedicines(medicines, {
                navController.navigate(NEW_MEDICINE_ROUTE)
            }) { medicine ->
                viewModel.deleteMedicine(medicine)
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

@ExperimentalFoundationApi
@Composable
fun ShowMedicines(
    medicines: List<Medicine>,
    AddNewMedicineClick: () -> Unit,
    OnMedicineDeletedClick: (Medicine) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                content = { Text(text = "+") },
                onClick = { AddNewMedicineClick() })
        },
    ) {
        if (medicines.isEmpty()) {
            ShowEmptyListPage()
        } else {
            ShowMedicinesListPage(medicines, OnMedicineDeletedClick)
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

@ExperimentalFoundationApi
@Composable
fun ShowMedicinesListPage(medicines: List<Medicine>, OnMedicineDeletedClick: (Medicine) -> Unit) {
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
            MedicineItem(medicine, OnMedicineDeletedClick)
        }
    }
}

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MedicineItem(medicine: Medicine, OnMedicineDeletedClick: (Medicine) -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .combinedClickable(
                onClick = {

                },
                onLongClick = { OnMedicineDeletedClick(medicine) }
            ),
        elevation = 4.dp,
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

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewList() {
    ShowMedicines(medicines = listOf(Medicine(1, "name", "description")), {}) {}
}