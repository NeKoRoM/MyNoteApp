package com.example.mynoteapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.navigation.NavRote

@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navHostController.navigate(NavRote.Add.route) }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")

        }
    }) { values ->

Column {
    NoteItem(title = "note1", subtitle ="nlasdfj note1" , navHostController = navHostController  )
    NoteItem(title = "note2", subtitle ="nlasdfj note2" , navHostController = navHostController  )
    NoteItem(title = "note3", subtitle ="nlasdfj note3" , navHostController = navHostController  )
    NoteItem(title = "note4", subtitle ="nlasdfj note4" , navHostController = navHostController  )

}



    }
}

@Composable
fun NoteItem(title: String, subtitle: String, navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable { navHostController.navigate(NavRote.Note.route) },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,

                )

        }


    }
    
}

@Preview(showBackground = true)
@Composable
fun previewMainScreen() {
    MainScreen(navHostController = rememberNavController())

}