package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.*
import java.lang.ArithmeticException

fun main() = runBlocking {

    val handler = CoroutineExceptionHandler{_, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    /*全局作用域协程*/
    val doJob = GlobalScope.launch(handler) {
        /*子协程*/
        launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable){
                    println("111111111111111111111111")
                    delay(100L)
                    println("222222222222222222222222")
                }
            }
        }

        /*子协程*/
        launch {
            delay(100L)
            println("33333333333333333333333333")
            throw ArithmeticException()
        }
    }
    doJob.join()

}