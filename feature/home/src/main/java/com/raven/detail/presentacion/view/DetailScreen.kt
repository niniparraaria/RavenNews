package com.raven.detail.presentacion.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninestudios.ui.ImageItem
import com.ninestudios.ui.RavenBackTopBar
import com.ninestudios.ui.ui.theme.RavenNewsTheme
import com.ninestudios.ui.ui.theme.white
import com.raven.home.domain.models.NewsData

@Composable
fun DetailItem(data:NewsData.Result, onBack:() -> Unit){
    Column(modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxSize().background(white)) {
        val images = data.media.firstOrNull()?.mediaMetadata?.maxByOrNull { it.height }?.url
        RavenBackTopBar(onClick = {
            onBack.invoke()
        })
        ImageItem(imageUrl = images, modifier = Modifier
            .fillMaxWidth()
            .height(200.dp))
        Text(
            modifier = Modifier.fillMaxWidth().padding(end = 16.dp, top = 8.dp),
            text = data.publishedDate,
            style = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.End,
                fontStyle = FontStyle.Italic
            )
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp).fillMaxWidth(),
            text = data.title,
            style = TextStyle(
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = data.abstract,
            style = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Justify
            )
        )
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp),
            text = data.byline,
            style = TextStyle(
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Start
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview(){
    RavenNewsTheme {
        val results = NewsData.Result("abstract", "byline", 1, emptyList(), "date","section", "source", "title", "type", "url")
        DetailItem(data = results, onBack = {})
    }
}


