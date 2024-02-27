package com.ninestudios.ui.views

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ninestudios.ui.ui.theme.RavenNewsTheme
import com.ninestudios.ui.ui.theme.white

@Composable
fun RavenTabs(listTabsNames:List<String>, onTab:(index:Int) -> Unit, tabIndex:Int = 0) {
    TabRow(selectedTabIndex = tabIndex, containerColor = white) {
        listTabsNames.forEachIndexed { index, title ->
            Tab(text = { Text(title) },
                selected = tabIndex == index,
                onClick = {
                    onTab.invoke(index)
                }
            )
        }
    }
}

@Preview
@Composable
fun RavenTabsPreview(){
    RavenNewsTheme {
        val tabs = listOf("Home", "About", "Settings")
        RavenTabs(tabs , onTab = {}, tabIndex = 2 )
    }
}