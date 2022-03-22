package com.savinoordine.medicinecompose.screen.core

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun DataPicker(selectedDate: (String) -> Unit) {

    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate("$dayOfMonth-${month+1}-$year")
        }, currentYear, currentMonth, currentDay
    )

    datePickerDialog.show()
}