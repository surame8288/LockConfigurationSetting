package com.test.lockconfiguration.ui.viewmodals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MutableStateAdapter<T>(
    private val state: State<T>,
    private val mutate: (T) -> Unit
) : MutableState<T> {
    override var value: T
        get() = state.value
        set(value) {
            mutate(value)
        }

    override fun component1(): T = value


    override fun component2(): (T) -> Unit = {
        value = it
    }

}

// Flow
@Composable
fun <T> MutableStateFlow<T>.collectAsMutableState(
    context: CoroutineContext = EmptyCoroutineContext
): MutableState<T> = MutableStateAdapter(
    state = collectAsState(context),
    mutate = { value = it }
)

//// LiveData
//@Composable
//fun <T> MutableLiveData<T>.observeAsMutableState(
//    initialValue: T
//): MutableState<T> = MutableStateAdapter(
//    state = observeAsState(initialValue),
//    mutate = { postValue(it) }
//)
//
//// RxJava 2/3
//@Composable
//fun <T> PublishSubject<T>.subscribeAsMutableState(
//    initialValue: T
//): MutableState<T> = MutableStateAdapter(
//    state = subscribeAsState(initialValue),
//    mutate = { onNext(it) }
//)