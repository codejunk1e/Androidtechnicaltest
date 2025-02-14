package com.bridge.androidtechnicaltest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PupilApp(viewModel: PupilViewModel) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "pupilList") {
        composable("pupilList") {
            PupilListScreen(
                viewModel = viewModel,
                onPupilClick = { pupil ->
                    viewModel.setSelectPupil(pupil)
                    navController.navigate("pupilDetail")
                },
                onAddPupilClick = {
                    viewModel.setSelectPupil(null)
                    navController.navigate("pupilDetail")
                }
            )
        }

        composable(
            route = "pupilDetail",
        ) {
            PupilDetailScreen(
                viewModel = viewModel,
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
