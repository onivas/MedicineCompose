package com.savinoordine.medicinecompose.screen.core

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.ui.theme.Green500

@ExperimentalMaterialApi
@Composable
fun MedicineListCardView(
    medicine: Medicine,
    onMedicineSelected: (Medicine) -> Unit,
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 72.dp, max = 80.dp)
        .padding(start=8.dp, top = 8.dp, bottom = 8.dp, end = 10.dp),
        backgroundColor = Green500,
        elevation = 4.dp,
        shape = CutCornerShape(bottomStartPercent = 15, topEndPercent = 30),
        onClick = { onMedicineSelected(medicine) }) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = medicine.name.replaceFirstChar { it.uppercase() })
            if (medicine.shortDescription.isNotEmpty()) {
                Text(
                    text = medicine.shortDescription.replaceFirstChar { it.uppercase() })
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewMedicineListCard() {
    MedicineListCardView(
        medicine = Medicine(name = "auricchio", shortDescription = "e magnalo spesso"),
        onMedicineSelected = {})
}