package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val numbers = produceNumbers()
    val squares = square(numbers)
    repeat(5) {
        println(squares.receive())
    }
    coroutineContext.cancelChildren()
    println("Done!!!")
}


@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
    }
}

@ExperimentalCoroutinesApi
fun CoroutineScope.square(numbers : ReceiveChannel<Int>) : ReceiveChannel<Int> = produce {
    for (x in numbers) {
        send(x * x)
    }
}