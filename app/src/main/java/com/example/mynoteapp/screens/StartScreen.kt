package com.example.mynoteapp.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.MainViewModel
import com.example.mynoteapp.MainViewModelFactory
import com.example.mynoteapp.navigation.NavRote
import com.example.mynoteapp.ui.theme.MyNoteAppTheme
import com.example.mynoteapp.utils.Constants
import com.example.mynoteapp.utils.LOGIN
import com.example.mynoteapp.utils.PASSWORD
import com.example.mynoteapp.utils.TYPE_FIREBASE
import com.example.mynoteapp.utils.TYPE_ROOM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navHostController: NavHostController, mViewModel: MainViewModel) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


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
            Text(text = Constants.Keys.WHAT_WE_WILL_USE)
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
                Text(text = Constants.Keys.ROOM_DATABASE)

            }
            Button(
                onClick = {

                    showBottomSheet = true
//                    mViewModel.initDatabase(TYPE_FIREBASE) {
//                        navHostController.navigate(route = NavRote.Main.route)
//                    }


                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = Constants.Keys.FIREBASE)

            }
        }

    }
    var isButtonEnabled by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            // Sheet content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)

            ) {
                Text(
                    text = Constants.Keys.LOGIN,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                TextField(
                    value = login,
                    onValueChange = {
                        login = it
                        isButtonEnabled = login.isNotEmpty() && password.isNotEmpty()
                    },
                    label = { Text(text = Constants.Keys.LOGIN) },
                    isError = login.isEmpty()
                )

                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isButtonEnabled = login.isNotEmpty() && password.isNotEmpty()
                    },
                    label = { Text(text = Constants.Keys.PASSWORD) },
                    isError = password.isEmpty()
                )

                Button(modifier = Modifier.padding(top = 16.dp, start = 32.dp, bottom = 40.dp),
                    onClick = {
                        LOGIN = login
                        PASSWORD = password
                        mViewModel.initDatabase(TYPE_FIREBASE) {

                        }


//                        scope.launch { sheetState.hide() }.invokeOnCompletion {
//                            if (!sheetState.isVisible) {
//                                showBottomSheet = false
//                            }
//                        }
                    }) {
                    Text(Constants.Keys.LOGIN)
                }
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