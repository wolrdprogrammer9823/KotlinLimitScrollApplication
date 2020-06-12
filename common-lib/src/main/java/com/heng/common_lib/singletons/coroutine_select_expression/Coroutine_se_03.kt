package com.heng.common_lib.singletons.coroutine_select_expression
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

fun main() = runBlocking {
    val side = Channel<Int>()
    launch {
        side.consumeEach { println("side has $it") }
    }

    produceNumbers(side).consumeEach {
        println("Consuming $it")
        delay(250L)
    }

    println("Consuming Done!!!")
    coroutineContext.cancelChildren()
}

suspend fun CoroutineScope.produceNumbers(send : SendChannel<Int>) = produce<Int> {
    for (num in 1..10) {
        delay(100L)
        send(num)
        send.send(num)
    }
}