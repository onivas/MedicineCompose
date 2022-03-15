package com.savinoordine.medicinecompose.screen.core

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savinoordine.medicinecompose.R

@Composable
fun TopBar(
    title: String? = null,
    onBackToListClicked: (() -> Unit)?,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(52.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            onBackToListClicked?.let {
                Button(
                    onClick = { it() }) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back_24),
                            contentDescription = ""
                        )
                        Text(text = "Back")
                    }
                }
            }

            title?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    text = title
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar("gino", null)
}