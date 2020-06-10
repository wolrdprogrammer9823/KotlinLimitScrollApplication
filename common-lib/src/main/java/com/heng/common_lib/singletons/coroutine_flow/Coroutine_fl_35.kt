package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {

    /*launchIn操作符开启了一条新协程  不会阻塞主协程*/

    (1..3).asFlow().onEach { value ->
                             delay(200L)
                             println("value $value") }
         .launchIn(this)

    println("Done!!!")
}