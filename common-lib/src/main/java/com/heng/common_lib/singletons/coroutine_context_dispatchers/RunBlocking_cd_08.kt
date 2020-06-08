package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking(CoroutineName("main")) {

    printLog("Started main coroutine")

    val a = async(CoroutineName("coroutine a")) {
        delay(500L)
        printLog("compute a")
        252
    }

    val b = async(CoroutineName("coroutine b")) {
        delay(1000L)
        printLog("compute b")
        6
    }

    printLog("The result of a / b is ${a.await() / b.await()}")
}

fun printLog(msg: String) = println("[${Thread.currentThread().name}] : $msg")