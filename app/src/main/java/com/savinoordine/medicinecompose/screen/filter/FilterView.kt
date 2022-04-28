package com.savinoordine.medicinecompose.screen.filter

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.R
import com.savinoordine.medicinecompose.screen.core.TopBar
import com.savinoordine.medicinecompose.ui.theme.Green10Alpha
import kotlin.math.roundToInt

const val MIN_PRICE = 0F
const val MAX_PRICE = 100F

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: FilterViewModel,
) {
    Crossfade(targetState = viewModel.state.collectAsState()) { state ->
        if (state.value.closeView) LaunchedEffect(Unit) { navController.popBackStack() }
        FilterView(
            onBackToListClicked = { navController.popBackStack() },
            onIsAtHomeClicked = { viewModel.isAtHomeClicked(it) },
            onIsExpiredClicked = { viewModel.isExpiredClicked(it) },
            onPriceChanged = { start, end -> viewModel.priceSliderChanged(start, end) },
            onFilterApplyClick = { viewModel.onFilterApplied() },
            onFilterCleanClick = { viewModel.onFilterCleaned() },
            isMedicineAtHome = state.value.filterPreference.isMedicineAtHome,
            isMedicineExpired = state.value.filterPreference.isMedicineExpired,
            minPrice = state.value.filterPreference.minPrice,
            maxPrice = state.value.filterPreference.maxPrice
        )
    }
}

@Composable
fun FilterView(
    onBackToListClicked: () -> Unit,
    onIsAtHomeClicked: (Boolean) -> Unit,
    onIsExpiredClicked: (Boolean) -> Unit,
    onPriceChanged: (Float, Float) -> Unit,
    onFilterApplyClick: () -> Unit,
    onFilterCleanClick: () -> Unit,
    isMedicineAtHome: Boolean,
    isMedicineExpired: Boolean,
    minPrice: Int,
    maxPrice: Int
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(onBackToListClicked = { onBackToListClicked() }) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            Column() {
                IsAtHomeFilterView(isMedicineAtHome) { onIsAtHomeClicked(it) }
                ExpireFilterView(isMedicineExpired) { onIsExpiredClicked(it) }
                PriceSliderFilterView(minPrice, maxPrice) { start, end ->
                    onPriceChanged(start, end)
                }
            }

            Column() {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.White
                    ),
                    onClick = { onFilterCleanClick() }) {
                    Row() {
                        Text(text = "Clean filter")
                    }

                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp),
                    onClick = { onFilterApplyClick() }) {
                    Row() {
                        Text(text = "Apply")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PriceSliderFilterView(
    minPrice: Int,
    maxPrice: Int,
    onPriceSelected: (Float, Float) -> Unit
) {
    val sliderPosition = remember { mutableStateOf(minPrice.toFloat()..maxPrice.toFloat()) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Green10Alpha)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "${MIN_PRICE.toInt()}€"
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "${MAX_PRICE.toInt()}€"
                )
            }
            RangeSlider(
                values = sliderPosition.value,
                valueRange = MIN_PRICE..MAX_PRICE,
                onValueChange = {
                    sliderPosition.value = it
                    onPriceSelected(it.start, it.endInclusive)
                })
            Text(text = "${sliderPosition.value.start.roundToInt()}€ - ${sliderPosition.value.endInclusive.roundToInt()}€")
        }
    }
}

@Composable
fun IsAtHomeFilterView(isMedicineAtHome: Boolean, onAtHomeChecked: (Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(isMedicineAtHome) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 12.dp)
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onAtHomeChecked(checkedState.value)
            },
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Is medicine at home?"
        )
    }
}

@Composable
fun ExpireFilterView(isMedicineExpired: Boolean, onExpiredChecked: (Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(isMedicineExpired) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 12.dp)
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onExpiredChecked(checkedState.value)
            },
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = "Is medicine expired?"
        )
    }
}


@Preview
@Composable
fun PreviewFilter() {
    FilterView(
        {},
        {},
        {},
        { _, _ -> },
        {},
        {},
        false,
        false,
        0,
        20
    )
}