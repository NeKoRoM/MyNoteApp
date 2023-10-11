package com.example.mynoteapp.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.MainViewModel
import com.example.mynoteapp.MainViewModelFactory
import com.example.mynoteapp.R
import com.example.mynoteapp.navigation.NavRote
import com.example.mynoteapp.ui.theme.MyNoteAppTheme
import com.example.mynoteapp.utils.TYPE_FIREBASE
import com.example.mynoteapp.utils.TYPE_ROOM

@Composable
fun StartScreen(navHostController: NavHostController, mViewModel: MainViewModel) {


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What we will use")
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_ROOM) {
                        navHostController.navigate(route = NavRote.Main.route)
                    }


                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.room_database))

            }
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_FIREBASE) {
                        navHostController.navigate(route = NavRote.Main.route)
                    }


                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = stringResource(R.string.fire_base))

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun previewStartScreen() {
    MyNoteAppTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        StartScreen(navHostController = rememberNavController(), mViewModel)
    }

}