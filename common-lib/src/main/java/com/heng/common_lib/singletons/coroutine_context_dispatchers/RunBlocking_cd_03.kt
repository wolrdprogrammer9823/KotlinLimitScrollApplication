package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    /*Edit Configurations...选项中 配置 vm Options: -Dkotlinx.coroutines.debug来区分协程*/

    val a = async {
        log("The one answer of computation")
        6
    }

    val b = async {
        log("The other answer of computation")
        10
    }

    log("The answer is:${a.await() + b.await()}")
}

fun log(msg: String) = println("${Thread.currentThread().name}->$msg")