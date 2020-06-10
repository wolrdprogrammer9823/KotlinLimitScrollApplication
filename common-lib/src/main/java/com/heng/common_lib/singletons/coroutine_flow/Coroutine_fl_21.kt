package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {

    val numbers = (1..3).asFlow().onEach { delay(300L) }
    val strings = flowOf("one", "two", "three").onEach { delay(400L) }
    val startTime = System.currentTimeMillis()
    numbers.zip(strings){
        a,b->
        "$a -> $b"
    }.collect {value -> println("$value at ${System.currentTimeMillis() - startTime} ms from start.")  }
}