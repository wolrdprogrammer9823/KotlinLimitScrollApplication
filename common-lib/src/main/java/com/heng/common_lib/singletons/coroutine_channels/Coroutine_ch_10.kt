package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {

    val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0)

    var nextElement = withTimeoutOrNull(1){ tickerChannel.receive()}
    println("Initial element is available immediately: $nextElement")

    nextElement = withTimeoutOrNull(50) { tickerChannel.receive()}
    println("Next element is not ready in 50 ms: $nextElement")

    nextElement = withTimeoutOrNull(60) { tickerChannel.receive()}
    println("Next element is ready in 100 ms: $nextElement")

    println("Consumer pauses for 150 ms")
    delay(150L)

    nextElement = withTimeoutOrNull(1) { tickerChannel.receive()}
    println("Next element is available immediately after large consume delay: $nextElement")

    nextElement = withTimeoutOrNull(60) { tickerChannel.receive()}
    println("Next element is ready in 150 ms after large consume delay: $nextElement")

    tickerChannel.cancel()
}