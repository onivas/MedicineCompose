package com.savinoordine.medicinecompose.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.savinoordine.medicinecompose.screen.core.TopBar
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
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                modifier = Modifier.wrapContentSize(),
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Black,
                text = medicine.name.replaceFirstChar { it.uppercase() }
            )
            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "How to use: "
                )
                Text(text = medicine.shortDescription)
            }

            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Price: "
                )
                Text(text = medicine.price)
            }

            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Is medicine at home? "
                )
                Text(text = if (medicine.isAtHome) "YES" else "NO")
            }

            Row(modifier = Modifier.padding(8.dp)) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Use before "
                )
                Text(text = ": ${medicine.expirationDate.orEmpty()}")
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = { onBackToListClicked() }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
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