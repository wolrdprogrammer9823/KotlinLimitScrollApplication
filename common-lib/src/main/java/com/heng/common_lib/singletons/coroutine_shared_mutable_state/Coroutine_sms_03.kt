package com.heng.common_lib.singletons.coroutine_shared_mutable_state
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

var counter3 = 0
val mutex = Mutex()

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun3 {
            mutex.withLock {
                counter3++
            }
        }
    }
    println("counter = $counter3")
}

suspend fun massiveRun3(action: suspend () -> Unit) {
    val m = 100
    val n = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(100) {
                launch {
                    repeat(1000) { action() }
                }
            }
        }
    }
    println("Completed ${m * n} in $time ms")
}