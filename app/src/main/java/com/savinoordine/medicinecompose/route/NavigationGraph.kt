package com.savinoordine.medicinecompose.route

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.savinoordine.medicinecompose.screen.list.MedicineList
import com.savinoordine.medicinecompose.screen.new.NewMedicineScreen

// routes
const val MAIN_ROUTE = "MAIN_ROUTE"
const val LIST_MEDICINE_ROUTE = "LIST_MEDICINE_ROUTE"
const val NEW_MEDICINE_ROUTE = "NEW_MEDICINE_ROUTE"

@ExperimentalMaterialApi
fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = LIST_MEDICINE_ROUTE, route = MAIN_ROUTE) {
        composable(route = LIST_MEDICINE_ROUTE) {
            MedicineList(navController = navController)
        }
        composable(route = NEW_MEDICINE_ROUTE) {
            NewMedicineScreen(navController = navController)
        }
    }
}