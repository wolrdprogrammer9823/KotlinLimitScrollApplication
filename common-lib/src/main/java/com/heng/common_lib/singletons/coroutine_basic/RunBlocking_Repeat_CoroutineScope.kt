package com.heng.common_lib.singletons.coroutine_basic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    /*全局作用域的协程 相当于守护线程*/
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I am $i")
            delay(500L)
        }
    }
    delay(2000L)
}