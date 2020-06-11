package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*Buffers Channel 缓冲通道*/
    val channel = Channel<Int>(4)
    val sender = launch {
        repeat(10) {
            println("Sender $it")
            channel.send(it)
        }
    }
    delay(1000L)
    sender.cancel()
}