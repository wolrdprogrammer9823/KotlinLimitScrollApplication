package com.heng.common_lib.singletons.coroutine_shared_mutable_state
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

//@Volatile  Volatile关键字保证可见性与有序性
//juc原子包下的类保证原子性 比如AtomicInteger()

//协程并发操作下  只有保证原子性  自增操作结果才正确  如下 1 2 3只有3结果正确

//1
//var counter = 0

//2
// @Volatile
//var counter = 0

// 3
var counter = AtomicInteger()

fun main() = runBlocking {

    withContext(Dispatchers.Default){
        massiveRun {
            //counter++
            counter.incrementAndGet()
        }
    }

    println("counter = $counter")
}

suspend fun massiveRun(action: suspend () -> Unit) {
    val k = 100
    val n = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(k) {
                launch {
                    repeat(n) {
                        action()
                    }
                }
            }
        }
    }
    println("Completed ${k * n} in $time ms")
}