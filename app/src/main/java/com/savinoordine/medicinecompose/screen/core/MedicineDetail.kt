package com.savinoordine.medicinecompose.screen.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.savinoordine.medicinecompose.R
import com.savinoordine.medicinecompose.domain.model.Medicine
import com.savinoordine.medicinecompose.ui.theme.Black

@Composable
fun MedicineDetail(
    selectedMedicine: Medicine?,
    onBackToListClicked: () -> Unit,
) {
    selectedMedicine?.let {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(onBackToListClicked = onBackToListClicked) },
        ) {
            MedicineBottomDetail(selectedMedicine, onBackToListClicked)
        }
    }
}

@Composable
fun MedicineBottomDetail(
    medicine: Medicine,
    onBackToListClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Black,
            text = medicine.name.replaceFirstChar { it.uppercase() }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = { onBackToListClicked() }) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back_24),
                    contentDescription = ""
                )
                Text(text = "Back to list")
            }

        }
    }
}


@Preview
@Composable
fun PreviewMedicineItem() {
    MedicineBottomDetail(medicine = Medicine(1, "aspiricchia", "description"), {})
}