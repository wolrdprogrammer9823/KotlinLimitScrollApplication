package com.heng.common_lib.singletons.coroutine_basic
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*局部作用域*/
fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!!")
    }
    print("Hello,")
}