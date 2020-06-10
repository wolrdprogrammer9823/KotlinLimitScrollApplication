package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow()
          .onEach { delay(100) }
          .flatMapLatest { value -> flowRequest(value) }
          .collect { value -> println("$value at ${System.currentTimeMillis() - startTime} ms from start.") }
}

fun flowRequest(i : Int) : Flow<String> = flow {
    emit("$i:First")
    delay(500L)
    emit("$i:Second")
}