package com.abdalla.jetnotecompose.utlis

import androidx.navigation.NavController
import com.abdalla.jetnotecompose.utlis.Destinations.AddNewNote
import com.abdalla.jetnotecompose.utlis.Destinations.Main
import com.abdalla.jetnotecompose.utlis.Destinations.Splash

object Destinations {
    const val Main = "main_screen"
    const val Splash = "splash_screen"
    const val AddNewNote = "add_new_note"

}

class Actions(navController: NavController) {
    val openMain: () -> Unit = {
        navController.navigate(Main)
    }
    val openSplash: () -> Unit = {
        navController.navigate(Splash)
    }
    val openAddNewNote: () -> Unit = {
        navController.navigate(AddNewNote)
    }
    val navigationUp: () -> Unit = {
        navController.popBackStack()
    }
    val  popUpToSplash:()->Unit={
        navController.navigate(Main){
            popUpTo(Splash) { //delete
                inclusive = true //close stack
            }
        }
    }
}