package com.heng.common_lib.singletons.coroutine_shared_mutable_state
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.system.measureTimeMillis

/*密封类*/
sealed class CounterMsg
/*对象*/
object IncCounter : CounterMsg()
/*类*/
class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg()

fun main() = runBlocking<Unit> {

    val counter = counterActor()
    //发送数据
    withContext(Dispatchers.Default){
        massiveRun4 {
            counter.send(IncCounter)
        }
    }
    //响应
    val response = CompletableDeferred<Int>()
    counter.send(GetCounter(response))
    println("Counter = ${response.await()}")
    counter.close()
}

@ObsoleteCoroutinesApi
fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0
    for (msg in channel) {
        when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

suspend fun massiveRun4(action: suspend () -> Unit) {
    val x = 100
    val y = 1000
    val time = measureTimeMillis {
        coroutineScope {
            repeat(x) {
                launch {
                    repeat(y) { action() }
                }
            }
        }
    }
    println("Completed ${x * y} in $time ms")
}
