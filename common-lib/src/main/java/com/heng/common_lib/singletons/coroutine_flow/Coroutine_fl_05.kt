package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    println("Calling foo5...")
    val foo5 = foo5()
    println("Calling collect...")
    foo5.collect { value -> println(value) }
    println("Calling collect again...")
    foo5.collect { value -> println(value) }
}

fun foo5() : Flow<Int> = flow {
    println("Flow started")
    for (i in 0..4) {
        delay(100L)
        emit(i)
    }
}