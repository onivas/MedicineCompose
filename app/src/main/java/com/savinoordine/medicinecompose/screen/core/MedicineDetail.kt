package com.savinoordine.medicinecompose.screen.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savinoordine.medicinecompose.R
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.ui.theme.Black
import com.savinoordine.medicinecompose.ui.theme.Red700

@Composable
fun MedicineDetail(
    selectedMedicine: Medicine?,
    onBackToListClicked: () -> Unit,
) {
    selectedMedicine?.let {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(52.dp)
                ) {
                    Button(onClick = { onBackToListClicked() }) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                                contentDescription = ""
                            )
                            Text(text = "Back")
                        }

                    }
                }
            },
            isFloatingActionButtonDocked = true,
        ) {
            MedicineBottomDetail(selectedMedicine)
        }
    }
}

@Composable
fun MedicineBottomDetail(medicine: Medicine) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Red700)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Black,
            text = medicine.name.replaceFirstChar { it.uppercase() }
        )
    }
}


@Preview
@Composable
fun PreviewMedicineItem() {
    MedicineBottomDetail(medicine = Medicine(1, "aspiricchia", "description"))
}