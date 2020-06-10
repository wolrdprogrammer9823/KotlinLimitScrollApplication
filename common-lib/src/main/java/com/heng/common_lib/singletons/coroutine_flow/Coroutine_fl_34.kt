package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*collect()会阻塞主协程*/
    (1..3).asFlow().onEach { value ->
                             delay(100)
                             println("value $value") }
          .collect()

    println("Done!!!")
}