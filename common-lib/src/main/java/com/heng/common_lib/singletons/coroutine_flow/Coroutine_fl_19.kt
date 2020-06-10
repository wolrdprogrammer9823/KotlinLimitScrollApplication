package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

@ExperimentalCoroutinesApi
fun main() = runBlocking {

    val time = measureTimeMillis {
        binary().collectLatest{value->
            println("emit $value")
            delay(300L)
            println("Done $value")
        }
    }
    println("Consumed time $time ms")
}

fun binary() : Flow<Int> = flow {
    for (i in 1..3) {
        delay(100L)
        emit(i)
    }
}