package com.liam.liamflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.liam.liamflix.ui.theme.LiamFlixTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			LiamFlixTheme {
				LiamFlixApp()
			}
		}
	}
}