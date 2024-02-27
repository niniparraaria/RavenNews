package com.raven.home.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ninestudios.ui.ImageItem
import com.raven.home.domain.models.NewsData

@Composable
fun NewsItem(
    news: NewsData.Result,
    onItemClick: (Long) -> Unit
) {
    val imageUrl = news.media.firstOrNull()?.mediaMetadata?.firstOrNull()?.url
    Row(
        modifier = Modifier
            .clickable { onItemClick(news.id) }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)) {
            ImageItem(imageUrl = imageUrl, modifier = Modifier.height(80.dp).width(80.dp))
        }
        Column() {
            Text(
                text = news.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = news.publishedDate,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                textAlign = TextAlign.Start
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp, start = 16.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NewsItemPreview(){
    val results = NewsData.Result("abstract", "byline", 1, listOf(), "date","section", "source", "title", "type", "url")
    NewsItem(news = results, onItemClick = {} )
}