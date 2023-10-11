package com.example.mynoteapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynoteapp.navigation.NotesNavHost
import com.example.mynoteapp.ui.theme.MyNoteAppTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoteAppTheme {

                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

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

                            NotesNavHost(mViewModel)

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

