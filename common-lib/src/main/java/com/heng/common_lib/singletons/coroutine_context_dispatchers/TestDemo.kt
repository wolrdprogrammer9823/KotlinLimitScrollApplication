package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.*

class TestDemo {

    /*
    * CoroutineScope 通常意义的作用域
    * MainScope UI引用作用域
    * */
    private val mainScope = CoroutineScope(Dispatchers.Default)
    //private val mainScope = MainScope()

    fun doSomething() {
        repeat(10) {i ->
            mainScope.launch {
                delay((i + 1) * 200L)
                println("coroutine $i is done")
            }
        }
    }

    fun destroy() {
        mainScope.cancel()
    }
}