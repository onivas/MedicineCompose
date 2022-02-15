package com.savinoordine.medicinecompose.screen.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.savinoordine.medicinecompose.domain.model.NoMedicine
import com.savinoordine.medicinecompose.domain.model.Pharma
import com.savinoordine.medicinecompose.route.NEW_MEDICINE_ROUTE
import com.savinoordine.medicinecompose.screen.core.State

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MedicineList(
    viewModel: MedicineListViewModel,
    navController: NavController
) {

    LaunchedEffect(Unit) { viewModel.fetchMedicines() }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val uiState = viewModel.uiState

    when (uiState.state) {
        State.IDLE -> {
            val medicines = uiState.medicines
            val selectedMedicine = uiState.selectedMedicine

            ShowMedicines(bottomSheetScaffoldState, medicines, selectedMedicine, {
                navController.navigate(NEW_MEDICINE_ROUTE)
            }, { medicine ->
                viewModel.deleteMedicine(medicine)
            }) { medicine ->
                viewModel.selectMedicine(medicine)
            }
        }
        State.LOADING -> {
            ShowLoading()
        }
        State.ERROR -> {
            uiState.error?.let { ShowError(it) }
        }
        State.SUCCESS -> {}
    }
}

@Composable
fun ShowError(error: String) {
    ShowEmptyListPage(error)
}

@Composable
fun ShowLoading() {
    LinearProgressIndicator(
        modifier = Modifier.height(3.dp),
        backgroundColor = Color.Green,
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun ShowMedicines(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    medicines: List<Medicine>,
    selectedMedicine: Pharma?,
    AddNewMedicineClick: () -> Unit,
    OnMedicineDeletedClick: (Medicine) -> Unit,
    OnMedicineClick: (Medicine) -> Unit
) {

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 80.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        floatingActionButton = {
            FloatingActionButton(
                content = { Text(text = "+") },
                onClick = { AddNewMedicineClick() })
        },
        floatingActionButtonPosition = FabPosition.Center,
        sheetContent = {
            ShowMedicineDetail(selectedMedicine)
        }
    ) {
        if (medicines.isEmpty()) {
            ShowEmptyListPage("Click + to add medicine")
        } else {
            ShowMedicinesListPage(medicines, OnMedicineDeletedClick, OnMedicineClick)
        }
    }
}

@Composable
fun ShowMedicineDetail(selectedMedicine: Pharma?) {
    when (selectedMedicine) {
        is Medicine -> ShowMedicineBottomDetail(selectedMedicine)
        else -> ShowNoMedicineBottomDetail(selectedMedicine as NoMedicine)
    }
}

@Composable
fun ShowMedicineBottomDetail(medicine: Medicine) {
    Row(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            color = Color.Magenta,
            text = medicine.name
        )
    }
}

@Composable
fun ShowNoMedicineBottomDetail(medicine: NoMedicine) {
    Row(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            color = Color.Magenta,
            text = medicine.name
        )
    }
}

@Composable
fun ShowEmptyListPage(message: String) {
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
            text = message
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun ShowMedicinesListPage(
    medicines: List<Medicine>,
    OnMedicineDeletedClick: (Medicine) -> Unit,
    OnMedicineClick: (Medicine) -> Unit
) {
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
            MedicineItem(medicine, OnMedicineDeletedClick, OnMedicineClick)
        }
    }
}

@ExperimentalFoundationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MedicineItem(
    medicine: Medicine,
    OnMedicineDeletedClick: (Medicine) -> Unit,
    OnMedicineClick: (Medicine) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .combinedClickable(
                onClick = {
                    OnMedicineClick(medicine)
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
//    ShowMedicines(medicines = listOf(Medicine(1, "name", "description")), {}) {}
}