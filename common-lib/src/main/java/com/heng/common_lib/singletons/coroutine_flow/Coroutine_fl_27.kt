package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    try {
        flowStr().collect { value -> println(value) }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}

fun flowStr() : Flow<String> =
    flow {
        for (i in 1..3) {
            println("emitting $i")
            emit(i)
        }
    }.map { value ->
        check(value <= 1) { "crashed on $value" }
        "string $value"
    }
