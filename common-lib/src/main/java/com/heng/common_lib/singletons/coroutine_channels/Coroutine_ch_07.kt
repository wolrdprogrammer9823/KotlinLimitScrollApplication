package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*fan-in 扇入*/
    val channel = Channel<String>()
    launch { sendStrings(channel, "foo", 200L) }
    launch { sendStrings(channel, "BAR", 500L) }
    repeat(6) {
        println(channel.receive())
    }
    channel.cancel()
}

suspend fun sendStrings(channel: SendChannel<String>, str: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(str)
    }
}