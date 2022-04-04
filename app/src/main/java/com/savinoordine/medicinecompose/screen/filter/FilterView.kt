package com.savinoordine.medicinecompose.screen.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savinoordine.medicinecompose.screen.core.TopBar
import com.savinoordine.medicinecompose.ui.theme.Green10Alpha
import kotlin.math.roundToInt

const val MIN_PRICE = 0F
const val MAX_PRICE = 100F

@Composable
fun FilterView(
    onBackToListClicked: () -> Unit,
    onFilterApplied: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(onBackToListClicked = onBackToListClicked) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            Column() {
                IsAtHomeFilterView {

                }
                ExpireFilterView {

                }
                PriceSliderFilterView { start, end ->

                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(8.dp),
                onClick = { onFilterApplied() }) {
                Text(text = "Apply")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PriceSliderFilterView(onPriceSelected: (Float, Float) -> Unit) {
    val sliderPosition = remember { mutableStateOf(MIN_PRICE..MAX_PRICE) }

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
fun IsAtHomeFilterView(onAtHomeChecked: (Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(false) }
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
fun ExpireFilterView(onExpiredChecked: (Boolean) -> Unit) {
    val checkedState = remember { mutableStateOf(false) }
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
    FilterView({}, {})
}