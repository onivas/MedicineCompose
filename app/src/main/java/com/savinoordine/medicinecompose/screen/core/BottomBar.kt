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
import androidx.navigation.NavController
import com.savinoordine.medicinecompose.R
import com.savinoordine.medicinecompose.route.FILTER_MEDICINE_ROUTE
import com.savinoordine.medicinecompose.route.LIST_MEDICINE_ROUTE
import com.savinoordine.medicinecompose.ui.theme.Red700

data class MenuItem(
    val route: String,
    val drawable: Int
)

private val menu = listOf(
    MenuItem(LIST_MEDICINE_ROUTE, R.drawable.ic_list_24),
    MenuItem(FILTER_MEDICINE_ROUTE, R.drawable.ic_filter_24)
)

@Composable
fun BottomBar(navController: NavController?) {
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
                icon = { Icon(painterResource(id = item.drawable), "") },
                onClick = {
                    navController?.navigate(item.route)
                },
                selectedContentColor = Color.White,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    BottomBar(null)
}