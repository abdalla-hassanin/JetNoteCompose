package com.abdalla.jetnotecompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.abdalla.jetnotecompose.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(popUpToSplash: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.charles_deluvio_yjys2an6v0i_unsplash),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }

    LaunchedEffect(true) { // Here, You need to use LaunchedEffect instead of Handle otherwise there will be an error
        delay(3000)
        popUpToSplash()
    }
}
