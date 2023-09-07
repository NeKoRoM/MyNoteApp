package com.example.mynoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynoteapp.navigation.NotesNavHost
import com.example.mynoteapp.ui.theme.MyNoteAppTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoteAppTheme {
                Scaffold(
                    topBar = {
                        Column {
                            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.Blue),
                                title = {
                                    Text(
                                        "Top AppBar",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis

                                    )
                                },
                                navigationIcon = {

                                },
                                actions = {

                                }
                            )
                            Spacer(modifier = Modifier.height(15.dp))

                        }

                    },
                    content = { innerPadding ->
                        Surface(modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)) {

                            NotesNavHost()

                        }


                    })
            }

        }
    }
}
@Preview(name = "ss", showBackground = true)
@Composable
fun Greeting() {
    Text(
        text = "Hello !",

    )
}


//@Preview(showBackground = true)

