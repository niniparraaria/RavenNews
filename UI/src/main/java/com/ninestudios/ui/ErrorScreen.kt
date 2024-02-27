package com.ninestudios.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ninestudios.ui.ui.theme.RavenNewsTheme
import com.ninestudios.ui.ui.theme.white
import com.raven.ui.R

@Composable
fun ErrorScreen(modifier: Modifier, onTryAgain:() -> Unit){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_error_404),
            contentDescription = "Image",
            contentScale = ContentScale.Inside,
            modifier = modifier.fillMaxSize()
        )
        Button(onClick = { onTryAgain.invoke()}, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp)) {
            Text(text = "Try Again")
        }
    }
}

@Preview
@Composable
fun ErrorPreview(){
    RavenNewsTheme {
        ErrorScreen(modifier = Modifier
            .fillMaxSize()
            .background(white), onTryAgain = {})
    }
}