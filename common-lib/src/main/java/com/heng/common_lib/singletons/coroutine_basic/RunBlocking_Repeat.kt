package com.heng.common_lib.singletons.coroutine_basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    /*创建100000个协程  延迟1000L后开始执行*/
    repeat(1000000) {
        launch {
            delay(1000L)
            println("Hello,World!!")
        }
    }
}