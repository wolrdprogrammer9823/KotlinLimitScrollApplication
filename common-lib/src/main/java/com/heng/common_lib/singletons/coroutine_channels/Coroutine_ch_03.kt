package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {

    val squares = produceSquares()
    squares.consumeEach { value-> println(value) }
    println("Done!!!")
}

@ExperimentalCoroutinesApi
fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) {
        send(x * x)
    }
}