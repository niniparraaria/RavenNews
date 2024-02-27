package com.ninestudios.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninestudios.ui.ui.theme.RavenNewsTheme
import com.ninestudios.ui.ui.theme.black
import com.ninestudios.ui.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RavenBackTopBar(onClick:() -> Unit ){
    TopAppBar(
        title = { Text(text = "" ) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = white, titleContentColor = black),
        navigationIcon = {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                tint = black,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        onClick.invoke()
                    },
                contentDescription = "back icon"
            )
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RavenTopBar(title:String ){
    TopAppBar(
        title = { Text(text = title, fontSize = 24.sp ) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = white, titleContentColor = black)
    )
}
@Preview
@Composable
fun RavenTopBarPreview(){
    RavenNewsTheme {
        RavenTopBar("Title")
    }
}
@Preview
@Composable
fun TopBarPreview(){
    RavenNewsTheme {
        RavenBackTopBar(onClick = {})
    }
}