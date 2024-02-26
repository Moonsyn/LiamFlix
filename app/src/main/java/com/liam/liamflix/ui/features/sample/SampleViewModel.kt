package com.liam.liamflix.ui.features.sample

import androidx.lifecycle.ViewModel
import com.liam.liamflix.domain.usecase.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

// Implement the ContainerHost interface
// Override the container field and use the ViewModel.container factory function
// to build an Orbit Container in your ContainerHost
@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase
) : ViewModel(), ContainerHost<SampleState, SampleSideEffect> {
    // Include `orbit-viewmodel` for the factory function
    override val container = container<SampleState, SampleSideEffect>(SampleState.Success())

    // Intent Block: Contains business logic journeys,
    // allows you to invoke other operators within
    fun sampleToast(number: Int) = intent {
        sampleUseCase(number).onSuccess { result ->
            // Sends one-off events to the side effect channel
            postSideEffect(SampleSideEffect.SendToast("Updating $number to ${result}!"))
            // Reduction: Atomically updates the Container's state
            reduce {
                (state as SampleState.Success).run {
                    copy(number = result)
                }
            }
        }.onFailure {
            postSideEffect(SampleSideEffect.ReportError(it))
        }
    }
}