package com.heng.common_lib.singletons.coroutine_select_expression
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import kotlin.random.Random

fun main() = runBlocking {

    val list = asyncStringList()
    val value = select<String> {
        list.withIndex().forEach { (index,deferred) ->
            deferred.onAwait { answer ->
                "Deferred $index produced answer '$answer'"
            }
        }
    }
    println(value)

    val countAlive = list.count { it.isActive }
    println("$countAlive coroutines is alive.")
}

fun CoroutineScope.doAsyncString(time: Int) = async {
    delay(timeMillis = time.toLong())
    "wait for $time ms"
}

fun CoroutineScope.asyncStringList() : List<Deferred<String>>{
    val random = Random(3)
    return List(12) {doAsyncString(random.nextInt(1000))}
}