package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

data class Ball(var hit: Int)

fun main() = runBlocking {

    /*channel是公平的  遵循FIFO的原则*/
    val table = Channel<Ball>()
    launch { playBall("ping", table) }
    launch { playBall("pong", table) }
    table.send(Ball(0))
    delay(800L)
    coroutineContext.cancelChildren()
}

suspend fun playBall(name: String, table: Channel<Ball>) {
    for (ball in table) {
        ball.hit++
        println("$name $ball")
        delay(200L)
        table.send(ball)
    }
}