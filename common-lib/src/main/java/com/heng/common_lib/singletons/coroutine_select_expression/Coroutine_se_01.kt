package com.heng.common_lib.singletons.coroutine_select_expression
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main() = runBlocking {

    val fizz = fizz()
    val buzz = buzz()
    repeat(7) {
        selectFizzBuzz(fizz, buzz)
    }
    coroutineContext.cancelChildren()
}

suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
    select<Unit> {
        fizz.onReceive{value->
            println("fizz value: $value")
        }
        buzz.onReceive{value->
            println("buzz value: $value")
        }
    }
}

fun CoroutineScope.fizz() = produce<String> {
    while (true) {
        delay(300L)
        send("fizz!!!")
    }
}

fun CoroutineScope.buzz() = produce<String> {
    while (true) {
        delay(500L)
        send("buzz...")
    }
}