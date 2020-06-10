package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    try {
        doFlow().collect { value ->
            println("value $value")
            check(value <= 1) { "collected $value" }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}

fun doFlow() : Flow<Int> = flow {
    for (i in 1..3) {
        println("flow $i")
        emit(i)
    }
}