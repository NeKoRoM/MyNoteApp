package com.example.mynoteapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.R
import com.example.mynoteapp.navigation.NavRote
import com.example.mynoteapp.ui.theme.MyNoteAppTheme

@Composable
fun StartScreen(navHostController: NavHostController) {
   Scaffold(
       modifier = Modifier
           .fillMaxSize())
   {
       Column(modifier = Modifier
           .padding(it)
           .fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
            Text(text = "What we will use")
           Button(onClick = { navHostController.navigate(route = NavRote.Main.route)},
               modifier = Modifier
                   .width(200.dp)
                   .padding(vertical = 8.dp)) {
               Text(text = stringResource(R.string.room_database))

           }
           Button(onClick = { navHostController.navigate(route = NavRote.Main.route) },
               modifier = Modifier
                   .width(200.dp)
                   .padding(vertical = 8.dp)) {
               Text(text = stringResource(R.string.fire_base))

           }
       }

   }
}

@Preview(showBackground = true)
@Composable
fun previewStartScreen() {
    MyNoteAppTheme {
        StartScreen(navHostController = rememberNavController())
    }
    
}