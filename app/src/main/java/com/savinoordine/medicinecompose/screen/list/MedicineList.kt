package com.savinoordine.medicinecompose.screen.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.R
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.route.NEW_MEDICINE_ROUTE
import com.savinoordine.medicinecompose.screen.core.*
import com.savinoordine.medicinecompose.screen.detail.MedicineDetail
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
            navController,
            state.value.medicines,
            { navController.navigate(NEW_MEDICINE_ROUTE) },
            { viewModel.selectMedicine(it) }
        ) { viewModel.deleteMedicine(it) }
        MedicineDetail(state.value.selectedMedicine) { viewModel.removeSelectedMedicine() }
        ErrorView(state.value.error)
    }
}

@ExperimentalMaterialApi
@Composable
fun ListContent(
    navController: NavController?,
    medicines: List<Medicine>,
    onNewMedicineClicked: () -> Unit,
    onMedicineSelected: (Medicine) -> Unit,
    onMedicineDeleted: (Medicine) -> Unit,
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(onBackToListClicked = null) },
        bottomBar = { BottomBar(navController) },
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
        MedicineList(medicines = medicines, onMedicineSelected, onMedicineDeleted)
    }
}

@ExperimentalMaterialApi
@Composable
fun MedicineList(
    medicines: List<Medicine>,
    onMedicineSelected: (Medicine) -> Unit,
    onMedicineDeleted: (Medicine) -> Unit,
) {
    val listState = rememberLazyListState()

    if (medicines.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No medicine yet")
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                text = "Medicines"
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                state = listState,
            ) {
                items(items = medicines) { medicine ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart) onMedicineDeleted(medicine)
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.4f) },
                        background = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 32.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                                    .background(color = Color.Red),
                                contentAlignment = Alignment.CenterEnd,
                            ) {
                                Icon(
                                    modifier = Modifier.padding(end = 4.dp),
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        },
                        dismissContent = {
                            MedicineListCardView(medicine = medicine, onMedicineSelected)
                        },
                    )
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
        null,
        listOf(medicine),
        {}, { Medicine(1, "name", "description") }, {})
}