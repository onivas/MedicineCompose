package com.savinoordine.medicinecompose.route

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.savinoordine.medicinecompose.screen.list.MedicineList
import com.savinoordine.medicinecompose.screen.create.CreateMedicineScreen
import com.savinoordine.medicinecompose.screen.filter.FilterView

// routes
const val MAIN_ROUTE = "MAIN_ROUTE"
const val LIST_MEDICINE_ROUTE = "LIST_MEDICINE_ROUTE"
const val FILTER_MEDICINE_ROUTE = "FILTER_MEDICINE_ROUTE"
const val NEW_MEDICINE_ROUTE = "NEW_MEDICINE_ROUTE"

@ExperimentalFoundationApi
@ExperimentalMaterialApi
fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = LIST_MEDICINE_ROUTE, route = MAIN_ROUTE) {
        composable(route = LIST_MEDICINE_ROUTE) {
            MedicineList(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
        composable(route = NEW_MEDICINE_ROUTE) {
            CreateMedicineScreen(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
        composable(route = FILTER_MEDICINE_ROUTE) {
            FilterView({}, {})
        }
    }
}