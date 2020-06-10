package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    playFlow().catch { value ->emit("caught $value") }.collect { value -> println(value) }
}

fun playFlow() : Flow<String> =
    flow {
        for (i in 1..3) {
            println("emitting $i")
            emit(i)
        }
    }.map { value ->
        check(value <= 1) {"crashed on $value"}
        "string $value"
    }

