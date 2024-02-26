package com.liam.liamflix.ui.features.sample

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SampleScreen(
	viewModel: SampleViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	val state by viewModel.collectAsState()

	viewModel.collectSideEffect {
		when (it) {
			is SampleSideEffect.SendToast -> {
				Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
			}

			is SampleSideEffect.ReportError -> {
				Toast.makeText(context, it.exception.message ?: "", Toast.LENGTH_SHORT).show()
			}
		}
	}

	when (state) {
		is SampleState.Error -> {
			Text(text = (state as SampleState.Error).exception.message ?: "")
		}
		SampleState.Loading -> {
			// show progress bar
		}
		is SampleState.Success -> {
			val success = state as SampleState.Success
			Column {
				Text(text = success.number.toString())

				Button(
					onClick = {
						viewModel.sampleToast(success.number)
					}
				) {
					Text(text = "숫자 늘리기")
				}
			}
		}
	}
}