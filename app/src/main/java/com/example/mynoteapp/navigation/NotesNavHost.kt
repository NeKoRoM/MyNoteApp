package com.example.mynoteapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.screens.Add
import com.example.mynoteapp.screens.Main
import com.example.mynoteapp.screens.Note
import com.example.mynoteapp.screens.Start

sealed class NavRote(val route: String) {
    object Start: NavRote("start_screen")
    object Main: NavRote("main_screen")
    object Add: NavRote("add_screen")
    object Note: NavRote("note_screen")


}

@Composable
fun NotesNavHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRote.Start.route ){
            composable(NavRote.Start.route){
                Start(navHostController = navController)
            }
        composable(NavRote.Main.route){
            Main(navHostController = navController)
        }
        composable(NavRote.Add.route){
            Add(navHostController = navController)
        }
        composable(NavRote.Note.route){
            Note(navHostController = navController)
        }



    }
}