package com.liam.liamflix.ui.features.sample

// The state and side effect objects must be comparable
// and we also recommend they be immutable.
// We suggest using a mix of data classes, sealed classes and objects.
sealed class SampleSideEffect {
    class SendToast(val message: String): SampleSideEffect()
    class ReportError(val exception: Throwable): SampleSideEffect()
}
