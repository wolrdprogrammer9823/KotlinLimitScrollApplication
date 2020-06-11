package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*fan-out 扇出*/
    val producer = createNumbers()
    repeat(5) { launchProcessor(it,producer) }
    delay(1000L)
    producer.cancel()
}

fun CoroutineScope.createNumbers() = produce<Int> {
    var start = 1
    while (true) {
        send(start++)
        delay(100L)
    }
}

fun CoroutineScope.launchProcessor(id :Int,numbers : ReceiveChannel<Int>)  = launch {
    for (x in numbers) {
        println("processor $id received $x")
    }
}

