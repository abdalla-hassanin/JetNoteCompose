package com.abdalla.jetnotecompose.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdalla.jetnotecompose.database.model.Note
import com.abdalla.jetnotecompose.database.viewModel.NoteViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNewNote(navigationUp: () -> Unit, noteViewModel: NoteViewModel) {
    val noteContent = remember { mutableStateOf(TextFieldValue()) }
    val noteTitle = remember { mutableStateOf(TextFieldValue()) }
    val snackBarVisibleState = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(navigationUp, noteContent, noteTitle, noteViewModel, snackBarVisibleState)
        },
        content = { ContentBody(noteContent, noteTitle) },
        snackbarHost = {
            if (snackBarVisibleState.value) {
                Snackbar(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Note is empty!!")
                }
            }
        },

        )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun TopBar(
    navigationUp: () -> Unit,
    noteContent: MutableState<TextFieldValue>,
    noteTitle: MutableState<TextFieldValue>,
    noteViewModel: NoteViewModel,
    snackBarVisibleState: MutableState<Boolean>
) {
    TopAppBar(
        title = {},
        actions = {
            Text(text = "Save",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(
                        onClick = {
                            if (noteContent.value.text.isNotEmpty() && noteTitle.value.text.isNotEmpty()) {
                                val currentDateTime = LocalDateTime.now()
                                noteViewModel.insertNote(
                                    Note(
                                        noteTitle.value.text,
                                        noteContent.value.text, currentDateTime.format(
                                            DateTimeFormatter.ofLocalizedDateTime(
                                                FormatStyle.LONG,
                                                FormatStyle.SHORT
                                            )
                                        )
                                    )
                                )
                                navigationUp()
                            } else {
                                snackBarVisibleState.value = true
                            }
                        }
                    ))
        },
        navigationIcon = {
            IconButton(onClick = {
                navigationUp()
            }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
private fun ContentBody(
    noteContent: MutableState<TextFieldValue>, noteTitle: MutableState<TextFieldValue>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            label = { Text(text = "Note Title") },
            value = noteTitle.value,
            onValueChange = { noteTitle.value = it },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            label = { Text(text = "Note Content") },
            value = noteContent.value,
            onValueChange = { noteContent.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            maxLines = Int.MAX_VALUE,
            textStyle = LocalTextStyle.current.copy(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 2.sp
            )
        )

    }
}
