package com.heng.common_lib.singletons.coroutine_basic
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*nested launch代码块和coroutine scope代码块谁先执行要看延迟时间  延迟时间相同的情况下 后者先执行*/

    launch {
        delay(200L)
        println("task from runBlocking")
    }

    coroutineScope {
        launch {
            delay(500L)
            println("task from nested launch")
        }

        delay(300L)
        println("Task from coroutine scope")
    }

    println("coroutine scope is over")
}