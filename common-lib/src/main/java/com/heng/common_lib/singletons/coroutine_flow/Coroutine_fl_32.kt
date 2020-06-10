package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
fun main() = runBlocking {

    setFlow().onCompletion { e-> if (e != null) println("Flow completion exceptionally") }
            .catch { println("caught exception") }
            .collect { value -> println("value $value") }
}

fun setFlow() : Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}