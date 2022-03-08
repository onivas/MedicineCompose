package com.savinoordine.medicinecompose.screen.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.R
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.route.NEW_MEDICINE_ROUTE
import com.savinoordine.medicinecompose.screen.core.BottomBar
import com.savinoordine.medicinecompose.screen.core.Loader
import com.savinoordine.medicinecompose.screen.core.MedicineDetail
import com.savinoordine.medicinecompose.ui.theme.Black

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MedicineList(
    viewModel: MedicineListViewModel,
    navController: NavController
) {

    LaunchedEffect(Unit) { viewModel.fetchMedicines() }

    Crossfade(targetState = viewModel.uiState.collectAsState()) { state ->
        Loader(state.value.isLoading)
        ListContent(
            state.value.medicines,
            { navController.navigate(NEW_MEDICINE_ROUTE) },
        ) { medicine ->
            viewModel.selectMedicine(medicine)

        }
        MedicineDetail(state.value.selectedMedicine)
        ErrorView(state.value.error)
    }
}

@ExperimentalMaterialApi
@Composable
fun ListContent(
    medicines: List<Medicine>,
    onNewMedicineClicked: () -> Unit,
    onMedicineSelected: (Medicine) -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNewMedicineClicked() },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            ) {
                Icon(painterResource(id = R.drawable.ic_add_24), "")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
    ) {
        MedicineList(medicines = medicines, onMedicineSelected)
    }
}

@ExperimentalMaterialApi
@Composable
fun MedicineList(
    medicines: List<Medicine>,
    onMedicineSelected: (Medicine) -> Unit,
) {
    val listState = rememberLazyListState()

    if (medicines.isEmpty()) {
        Text(text = "No medicine yet")
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            state = listState,
        ) {
            items(items = medicines) { medicine ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(6.dp),
                    elevation = 4.dp,
                    onClick = { onMedicineSelected(medicine) }) {
                    Text(modifier = Modifier.padding(4.dp), text = medicine.name)
                }
            }
        }
    }
}

@Composable
fun ErrorView(error: String?) {
    error?.let { EmptyLisView(it) }
}


@Composable
fun EmptyLisView(message: String) {
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
            color = Black,
            text = message
        )
    }
}

@ExperimentalMaterialApi
@Preview(name = "Medicine Detail")
@Composable
fun PreviewList() {
    val medicine = Medicine(1, "name", "description")
    ListContent(
        listOf(medicine),
        {}, { Medicine(1, "name", "description") })
}