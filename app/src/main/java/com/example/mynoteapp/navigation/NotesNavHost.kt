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
import com.example.mynoteapp.utils.Constants
import com.example.mynoteapp.utils.Constants.Screens.ADD_SCREEN
import com.example.mynoteapp.utils.Constants.Screens.MAIN_SCREEN
import com.example.mynoteapp.utils.Constants.Screens.NOTE_SCREEN
import com.example.mynoteapp.utils.Constants.Screens.START_SCREEN

sealed class NavRote(val route: String) {
    object Start : NavRote(START_SCREEN)
    object Main : NavRote(MAIN_SCREEN)
    object Add : NavRote(ADD_SCREEN)
    object Note : NavRote(NOTE_SCREEN)


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
        composable(NavRote.Note.route + "/{${Constants.Keys.ID}}") { backStackEntery ->
            NoteScreen(
                navHostController = navController,
                mViewModel = mViewModel,
                noteID = backStackEntery.arguments?.getString(Constants.Keys.ID)
            )
        }



    }
}