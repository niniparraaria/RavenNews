package com.ninestudios.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ninestudios.ui.ui.theme.RavenNewsTheme
import com.ninestudios.ui.ui.theme.white

@Composable
fun LoadingScreen(modifier: Modifier ){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun LoadingPreview(){
    RavenNewsTheme {
        LoadingScreen(modifier = Modifier.fillMaxSize().background(white))
    }
}