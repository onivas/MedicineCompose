package com.savinoordine.medicinecompose.route

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.savinoordine.medicinecompose.list.MedicineList

// routes
const val MAIN_ROUTE = "MAIN_ROUTE"
const val MEDICINE_LIST_ROUTE = "BEER_LIST_ROUTE"

@ExperimentalMaterialApi
fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = MEDICINE_LIST_ROUTE, route = MAIN_ROUTE) {
        composable(
            route = MEDICINE_LIST_ROUTE
        ) {
            MedicineList(navController = navController)
        }
    }
}