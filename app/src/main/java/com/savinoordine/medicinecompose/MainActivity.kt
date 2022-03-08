package com.savinoordine.medicinecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.savinoordine.medicinecompose.route.MAIN_ROUTE
import com.savinoordine.medicinecompose.route.mainGraph
import com.savinoordine.medicinecompose.ui.theme.Green700
import com.savinoordine.medicinecompose.ui.theme.MedicineComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.green500)
        setContent {
            MedicineComposeTheme {
                Surface(color = Green700) {
                    MedicineApp()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MedicineApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE
    ) {
        mainGraph(navController = navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MedicineComposeTheme {
        Surface(color = Green700) {
            MedicineApp()
        }
    }
}