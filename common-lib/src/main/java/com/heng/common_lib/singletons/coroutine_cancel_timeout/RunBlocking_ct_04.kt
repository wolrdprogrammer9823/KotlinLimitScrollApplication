package com.heng.common_lib.singletons.coroutine_cancel_timeout
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val job = launch {
        try {
            repeat(1000){ i ->
                println("job: I am $i")
                delay(500L)
            }
        } finally {
            println("job:I am finally")
        }
    }

    delay(1300L)
    println("main:I am tried of waiting...")
    job.cancelAndJoin()
    println("main:Now I can exit")
}