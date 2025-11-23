package br.com.spike

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

interface EventEmitter<T> {
    public val events: Flow<T>
    public fun emitEvent(event: T)
}

class EventEmitterHandler<T> : EventEmitter<T> {
    private val _events = MutableSharedFlow<T>()
    override val events: Flow<T>
        get() = _events.asSharedFlow()

    override fun emitEvent(event: T) {
        MainScope().launch {
            _events.emit(event)
        }
    }
}

@Composable
fun <T> EventEffect(
    eventEmitter: EventEmitter<T>,
    onEvent: (T) -> Unit,
) {
    LaunchedEffect(eventEmitter) {
        eventEmitter.events.collectLatest(onEvent)
    }
}