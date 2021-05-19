package com.abdalla.jetnotecompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abdalla.jetnotecompose.R
import com.abdalla.jetnotecompose.utlis.ScreenName
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(modifier = Modifier.background(Color.Unspecified)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }
    }
    LaunchedEffect(true) { // Here, You need to use LaunchedEffect instead of Handle otherwise there will be an error
        delay(3000)
        navController.navigate(ScreenName.MAINSCREEN.screenName) {
            popUpTo(ScreenName.SPLASHSCREEN.screenName) { //delete
                inclusive = true //close stack
            }
        }
    }
}