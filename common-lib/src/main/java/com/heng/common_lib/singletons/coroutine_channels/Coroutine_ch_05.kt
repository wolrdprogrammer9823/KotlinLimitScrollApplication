package com.heng.common_lib.singletons.coroutine_channels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

     var cur = fromNumbers(2)
     repeat(10) {
        val prime = cur.receive()
        println("prime $prime")
        cur = filter(cur, prime)
     }
     coroutineContext.cancelChildren()

    //TODO() 不清楚执行流程
}

fun CoroutineScope.fromNumbers(number: Int) = produce {
    var x = number
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.filter(number : ReceiveChannel<Int>, prime : Int) : ReceiveChannel<Int> = produce {
    //println("number ${number.receive()}")
    for (x in number) {
        if (x % prime != 0) {
            send(x)
        }
    }
}
