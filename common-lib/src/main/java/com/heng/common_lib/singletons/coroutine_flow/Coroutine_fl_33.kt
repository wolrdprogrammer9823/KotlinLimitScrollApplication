package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {

    (1..3).asFlow()
          .onCompletion { cause -> println("Flow completed with $cause") }
          .collect { value ->
            check(value <= 1) {"collected $value"}
            println("value $value")
          }

}