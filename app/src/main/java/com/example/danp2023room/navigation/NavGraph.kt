package com.example.danp2023room.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.danp2023room.navigation.Screen.*
import com.example.danp2023room.presentation.ListaCursosEstudiantesScreen
import com.example.danp2023room.presentation.RoomSample


@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = MainScreen.route){
        composable(
            route = MainScreen.route
        ){
            RoomSample(
                navigateToListaEstudiantesPorCurso = {
                    navController.navigate("${ListaEstudiantesPorCurso.route}")
                }
            )
        }
        composable(
            route = ListaEstudiantesPorCurso.route
        ){
            ListaCursosEstudiantesScreen()
        }
    }

}