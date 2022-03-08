package com.savinoordine.medicinecompose.screen.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.savinoordine.medicinecompose.R
import com.savinoordine.medicinecompose.ui.theme.Red700

private val menu = listOf(
    R.drawable.ic_list_24,
    R.drawable.ic_filter_24
)

@Composable
fun BottomBar() {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
        contentColor = MaterialTheme.colors.onPrimary,
        cutoutShape = CircleShape,
        elevation = AppBarDefaults.BottomAppBarElevation,
    ) {
        menu.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = index == 0,
                alwaysShowLabel = false,
                icon = { Icon(painterResource(id = item), "") },
                onClick = { /*TODO*/ },
                selectedContentColor = Color.White,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    BottomBar()
}