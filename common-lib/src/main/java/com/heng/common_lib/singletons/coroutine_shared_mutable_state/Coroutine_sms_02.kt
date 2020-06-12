package com.heng.common_lib.singletons.coroutine_shared_mutable_state
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

var counter2 = 0
@ObsoleteCoroutinesApi
val counter2Context = newSingleThreadContext("CounterContext")

@ObsoleteCoroutinesApi
fun main() = runBlocking {

    /*
    * 细粒度运算 vs 粗粒度运算  后者运行速度更快
    * */

    //细粒度运算
    withContext(Dispatchers.Default) {
        massiveRun2 {
            //细粒度运算
            withContext(counter2Context){
                counter2++
            }
        }
    }

    //粗粒度运算
//    withContext(counter2Context) {
//        massiveRun2 {
//            counter2++
//        }
//    }

    println("counter = $counter2")
}

suspend fun massiveRun2(action: suspend () -> Unit) {

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

    println("completed ${m * n} in $time ms")
}