package com.liam.liamflix.ui.features.sample

// The state and side effect objects must be comparable
// and we also recommend they be immutable.
// We suggest using a mix of data classes, sealed classes and objects.
sealed class SampleState {
    object Loading: SampleState()

    data class Success(
        val number: Int = 0
    ): SampleState()

    data class Error(
        val exception: Throwable,
        val isNetworkError: Boolean = false
    ): SampleState()
}