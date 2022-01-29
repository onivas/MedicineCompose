package com.savinoordine.medicinecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.savinoordine.medicinecompose.route.MAIN_ROUTE
import com.savinoordine.medicinecompose.route.mainGraph
import com.savinoordine.medicinecompose.ui.theme.MedicineComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicineComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MedicineApp()
                }
            }
        }
    }
}

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
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MedicineComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            MedicineApp()
        }
    }
}