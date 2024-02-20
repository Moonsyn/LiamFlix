package com.liam.liamflix.ui.features.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onSearchClicked: () -> Unit
) {
    Button(onClick = onSearchClicked) {
        Text(text = "검색하기")
    }
    Text(text = "Home")
}