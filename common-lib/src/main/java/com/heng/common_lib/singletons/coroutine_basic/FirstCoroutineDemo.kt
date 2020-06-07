package com.heng.common_lib.singletons.coroutine_basic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    /*全局作用域*/
    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }

    print("Hello,")
    Thread.sleep(2000L)
}