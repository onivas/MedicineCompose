package com.savinoordine.medicinecompose.screen.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.domain.model.NoMedicine
import com.savinoordine.medicinecompose.domain.model.Pharma
import com.savinoordine.medicinecompose.ui.theme.Black
import com.savinoordine.medicinecompose.ui.theme.Red700

@Composable
fun MedicineDetail(selectedMedicine: Pharma?) {
    when (selectedMedicine) {
        is Medicine -> MedicineBottomDetail(selectedMedicine)
        else -> NoMedicineView(selectedMedicine as NoMedicine)
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

@Composable
fun NoMedicineView(medicine: NoMedicine) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            textAlign = TextAlign.Center,
            color = Black,
            text = medicine.name
        )
    }
}

@Preview
@Composable
fun PreviewMedicineItem() {
    MedicineBottomDetail(medicine = Medicine(1, "aspiricchia", "description"))
}