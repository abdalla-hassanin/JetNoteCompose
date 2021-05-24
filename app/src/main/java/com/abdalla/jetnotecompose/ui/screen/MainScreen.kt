package com.abdalla.jetnotecompose.ui.screen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.twotone.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.abdalla.jetnotecompose.R
import com.abdalla.jetnotecompose.database.model.Note
import com.abdalla.jetnotecompose.database.viewModel.NoteViewModel
import com.abdalla.jetnotecompose.utlis.ScreenName

@Composable
fun MainScreen(navController: NavController, noteViewModel: NoteViewModel, context: Context) {
    val scaffoldState = rememberScaffoldState()
    val getAllNotes by noteViewModel.getAllNotes.observeAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        content = { getAllNotes?.let { ContentBody(it, noteViewModel, context) } },
        floatingActionButton = { FloatingButton(navController) }
    )

}


@Composable
private fun TopBar() {
    TopAppBar(
        title = { Text(text = "MyNote") },
        actions = {
            IconButton(onClick = {
                // TODO:  adds search
            }) {
                Icon(Icons.TwoTone.Search, contentDescription = null, tint = Color.White)
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                // TODO:  navigate to drawer
            }
            ) {
                Icon(Icons.Rounded.Menu, contentDescription = null)
            }
        }
    )
}

@Composable
private fun FloatingButton(navController: NavController) {
    FloatingActionButton(onClick = {
        navController.navigate(ScreenName.ADDNEWNOTE.screenName)
    }) {
        Icon(
            imageVector = Icons.Default.NoteAdd,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun ContentBody(notes: List<Note>, noteViewModel: NoteViewModel, context: Context) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.andrik_langfield_1_yqioijio8_unsplash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(notes) {
                    Card(
                        elevation = 10.dp,
                        modifier = Modifier.padding(20.dp),
                        content = {
                            Column(
                                modifier = Modifier
                                    .clickable {
                                        //TODO: When click on card
                                    },
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    it.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.ExtraBold,
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, top = 10.dp)
                                )
                                Text(
                                    it.content,
                                    maxLines = 7,
                                    overflow = TextOverflow.Ellipsis,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, top = 10.dp)
                                )


                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = it.time,
                                        modifier = Modifier.padding(8.dp),
                                        fontWeight = FontWeight.W800,
                                        style = MaterialTheme.typography.body2,
                                        color = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.weight(1F))
                                    IconButton(onClick = {
                                        //TODO : Edit note
                                    }) {
                                        Icon(
                                            (Icons.Rounded.Create),
                                            contentDescription = null,
                                        )
                                    }
                                    IconButton(onClick = {
                                        val sendIntent: Intent = Intent().apply {
                                            action = Intent.ACTION_SEND
                                            putExtra(Intent.EXTRA_TEXT, it.title)
                                            putExtra(Intent.EXTRA_TEXT, it.content)
                                            type = "text/plain"
                                        }
                                        val shareIntent =
                                            Intent.createChooser(sendIntent, "Share With ::")
                                        ContextCompat.startActivity(context, shareIntent, Bundle())
                                    }) {
                                        Icon(
                                            Icons.Rounded.Share,
                                            contentDescription = null,
                                        )
                                    }
                                    IconButton(onClick = {
                                        noteViewModel.deleteNote(it)
                                    }) {
                                        Icon(
                                            Icons.Rounded.Delete,
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }
                        })
                }
            }
        }
    }
}