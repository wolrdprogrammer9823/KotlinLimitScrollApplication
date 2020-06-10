package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {

    val numbers = (1..3).asFlow()
    val strings = flowOf("one", "two", "three")
    numbers.zip(strings){a,b->"$a -> $b" }
           .collect { value -> println(value) }
}