package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch {
        for (k in 0..4) {
            println("I am not blocked $k")
            delay(100L)
        }
    }
    foo4().collect { value-> println(value) }
}

fun foo4() : Flow<Int> = flow {
    for (i in 0..4) {
        delay(100L)
        emit(i)
    }
}