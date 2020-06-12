package com.heng.common_lib.singletons.coroutine_select_expression
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.selects.select

fun main() = runBlocking<Unit> {

    val chan = Channel<Deferred<String>>()
    launch {
        for (s in switchModeToMap(chan)) {
            println("value $s")
        }
    }

    chan.send(asyncString("Begin", 100L))
    delay(200L)

    chan.send(asyncString("Slow", 500L))
    delay(100L)

    chan.send(asyncString("Replace", 100L))
    delay(500L)

    chan.send(asyncString("End", 500L))
    delay(1000L)

    chan.close()
    delay(500L)
}

fun CoroutineScope.switchModeToMap(input: ReceiveChannel<Deferred<String>>) = produce {

    var current = input.receive()
    while (isActive) {
        val next = select<Deferred<String>?> {
            input.onReceiveOrNull{ update -> update }
            current.onAwait {value->
                send(value)
                input.receiveOrNull()
            }
        }

        if (next == null) {
            println("channel is closed")
            break
        } else {
            current = next
        }
    }
}

fun CoroutineScope.asyncString(str :String,time : Long) = async {
    delay(time)
    str
}