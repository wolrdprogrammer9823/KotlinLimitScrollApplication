package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    foo13().collect { value -> log13("collected $value") }
}

fun log13(msg : String) = println("[${Thread.currentThread().name}] $msg")

fun foo13() : Flow<Int> = flow {
    println("Started to flow")
    for (i in 1..3) {
        emit(i)
    }
}