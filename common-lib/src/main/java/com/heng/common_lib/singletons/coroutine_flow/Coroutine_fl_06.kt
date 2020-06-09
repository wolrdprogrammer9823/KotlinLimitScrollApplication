package com.heng.common_lib.singletons.coroutine_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking<Unit> {
    withTimeoutOrNull(250) {
        foo6().collect { value -> println(value) }
    }
    println("Done!")
}

fun foo6() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100L)
        println("emit $i")
        emit(i)
    }
}