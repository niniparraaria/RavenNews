package com.ninestudios.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.ninestudios.ui.ui.theme.RavenNewsTheme
import com.raven.ui.R

@Composable
fun ImageItem(imageUrl: String? = null, modifier: Modifier = Modifier) {
    val painter = when{
        imageUrl.isNullOrEmpty()-> {
            painterResource(id = R.drawable.img_image_not_found)
        }
        else -> {
            rememberAsyncImagePainter(model = imageUrl)
        }
    }
    Image(
        painter = painter,
        contentDescription = "Image",
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}

@Preview
@Composable
fun ImageItemPreview(){
    RavenNewsTheme {
        ImageItem()
    }
}

