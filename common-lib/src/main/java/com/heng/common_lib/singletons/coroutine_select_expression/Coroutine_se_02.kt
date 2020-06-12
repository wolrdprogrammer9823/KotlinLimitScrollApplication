package com.heng.common_lib.singletons.coroutine_select_expression
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.onReceiveOrNull
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main() = runBlocking {

    val a = produce<String> {
        repeat(4) {
            send("a value $it")
        }
    }

    val b = produce<String> {
        repeat(4) {
            send("b value $it")
        }
    }

    repeat(8) {
        println(selectAorB(a, b))
    }

    coroutineContext.cancelChildren()
}


suspend fun selectAorB(a : ReceiveChannel<String>,b : ReceiveChannel<String>) : String =
    select {
        a.onReceiveOrNull { value ->
            if (value == null) {
                 "a is null"
            } else {
                "$value"
            }
        }

        b.onReceiveOrNull { value ->
            if (value == null) {
               "b is null"
            } else {
                "$value"
            }
        }
    }