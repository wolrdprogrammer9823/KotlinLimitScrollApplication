package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    foo15().collect { value -> log15("collected $value") }
}

fun log15(msg: String) = println("[${Thread.currentThread().name}] $msg")

@ExperimentalCoroutinesApi
fun foo15() : Flow<Int> = flow {
    for (i in 1..5) {
        Thread.sleep(1000L)
        log15("emit $i")
        emit(i)
    }
}.flowOn(Dispatchers.Default)