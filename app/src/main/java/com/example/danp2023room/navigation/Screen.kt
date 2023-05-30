package com.example.danp2023room.navigation

sealed class Screen(val route: String){
    object MainScreen: Screen("Pagina Inicio")
    object ListaEstudiantesPorCurso: Screen("Lista Estudiantes por Curso")
}
