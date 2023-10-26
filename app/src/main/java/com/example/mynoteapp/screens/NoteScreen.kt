package com.example.mynoteapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mynoteapp.MainViewModel
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.navigation.NavRote
import com.example.mynoteapp.utils.Constants
import com.example.mynoteapp.utils.Constants.Keys.UPDATE_NOTE
import com.example.mynoteapp.utils.DB_TYPE
import com.example.mynoteapp.utils.TYPE_FIREBASE
import com.example.mynoteapp.utils.TYPE_ROOM
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navHostController: NavHostController, mViewModel: MainViewModel, noteID: String?) {
    val notes = mViewModel.readAllNotes().observeAsState(listOf()).value
    val note = when (DB_TYPE) {
        TYPE_ROOM -> {
            notes.firstOrNull() { it.id == noteID?.toInt() } ?: Note()
        }

        TYPE_FIREBASE -> {
            notes.firstOrNull() { it.firebaseID == noteID } ?: Note()
        }

        else -> Note()
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)

        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = note.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = note.subtitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 16.dp)
                )

            }


        }
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                showBottomSheet = true
                title = note.title
                subtitle = note.subtitle

            }) {

                Text(text = Constants.Keys.UPDATE)
            }
            Button(onClick = {
                mViewModel.deleteNote(note) {
                    navHostController.navigate(NavRote.Main.route)
                }

            }) {

                Text(text = Constants.Keys.DELETE)
            }


        }
        Button(modifier = Modifier
            .padding(16.dp)
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
            onClick = {
                navHostController.navigate(NavRote.Main.route)

            }) {

            Text(text = Constants.Keys.NAW_BACK)
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
                    text = Constants.Keys.UPDATE_NOTE,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                        isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                    },
                    label = { Text(text = Constants.Keys.NOTE_TITLE) },
                    isError = title.isEmpty()
                )

                OutlinedTextField(
                    value = subtitle,
                    onValueChange = {
                        subtitle = it
                        isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                    },
                    label = { Text(text = Constants.Keys.NOTE_SUBTITLE) },
                    isError = subtitle.isEmpty()
                )

                Button(modifier = Modifier.padding(top = 16.dp, start = 32.dp),
                    onClick = {
                        mViewModel.updateNote(
                            note = Note(
                                id = note.id,
                                title = title,
                                subtitle = subtitle,
                                firebaseID = note.firebaseID
                            )
                        ) {
                            navHostController.navigate(NavRote.Main.route)
                        }

                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                    Text(UPDATE_NOTE)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    // NoteScreen(navHostController = rememberNavController(), mViewModel = mViewModel)

}