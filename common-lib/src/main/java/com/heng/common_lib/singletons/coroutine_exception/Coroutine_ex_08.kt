package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.*
import java.lang.AssertionError

fun main() = runBlocking {

    try {
        supervisorScope {

            /*子协程*/
            val child = launch {
                try {
                    println("214213131")
                    delay(Long.MAX_VALUE)
                } finally {
                    println("77777777777")
                }
            }

            yield()
            println("54314143")
            throw AssertionError()
        }
    } catch (e: AssertionError) {
        println("Caught asset error")
    }
}