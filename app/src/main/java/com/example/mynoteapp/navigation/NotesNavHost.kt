package com.example.mynoteapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.MainViewModel
import com.example.mynoteapp.screens.AddScreen
import com.example.mynoteapp.screens.MainScreen
import com.example.mynoteapp.screens.NoteScreen
import com.example.mynoteapp.screens.StartScreen

sealed class NavRote(val route: String) {
    object Start: NavRote("start_screen")
    object Main: NavRote("main_screen")
    object Add: NavRote("add_screen")
    object Note: NavRote("note_screen")


}

@Composable
fun NotesNavHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRote.Start.route) {
        composable(NavRote.Start.route) {
            StartScreen(navHostController = navController, mViewModel = mViewModel)
        }
        composable(NavRote.Main.route) {
            MainScreen(navHostController = navController, mViewModel = mViewModel)
        }
        composable(NavRote.Add.route) {
            AddScreen(navHostController = navController, mViewModel = mViewModel)
        }
        composable(NavRote.Note.route){
            NoteScreen(navHostController = navController, mViewModel = mViewModel)
        }



    }
}