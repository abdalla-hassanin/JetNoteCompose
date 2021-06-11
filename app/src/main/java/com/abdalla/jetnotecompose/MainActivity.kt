package com.abdalla.jetnotecompose

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdalla.jetnotecompose.database.viewModel.NoteViewModel
import com.abdalla.jetnotecompose.database.viewModel.NoteViewModelFactory
import com.abdalla.jetnotecompose.database.viewModel.NotesApplication
import com.abdalla.jetnotecompose.ui.screen.AddNewNote
import com.abdalla.jetnotecompose.ui.screen.MainScreen
import com.abdalla.jetnotecompose.ui.screen.SplashScreen
import com.abdalla.jetnotecompose.ui.theme.MyNoteTheme
import com.abdalla.jetnotecompose.utlis.Actions
import com.abdalla.jetnotecompose.utlis.Destinations.AddNewNote
import com.abdalla.jetnotecompose.utlis.Destinations.Main
import com.abdalla.jetnotecompose.utlis.Destinations.Splash

class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NotesApplication).repository)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoteTheme {
                MyApp(noteViewModel,this)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(noteViewModel: NoteViewModel, context:Context) {
    val navController = rememberNavController()
    val action = remember(navController) { Actions(navController) }
    NavHost(navController, startDestination = Splash) {
        composable(Splash) { SplashScreen(action.popUpToSplash) }
        composable(Main) { MainScreen(action.openAddNewNote, noteViewModel,context) }
        composable(AddNewNote) { AddNewNote(action.navigationUp, noteViewModel) }
    }
}