package com.heng.common_lib.singletons.coroutine_cancel_timeout
import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = launch {
        try {
            repeat(1000) { i ->
                println("job:I am from $i")
                delay(500L)
            }
        } finally {
            /*这一块的代码 不能取消掉*/
            withContext(NonCancellable) {
                println("job:I am running finally")
                delay(1000L)
                println("job:I am just delayed 1 sec because I am NonCancellable")
            }
        }
    }

    delay(1300L)
    println("main:I am tried of waiting")
    job.cancelAndJoin()
    println("main:Now I can exit")
}