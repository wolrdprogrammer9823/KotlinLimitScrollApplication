package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    /*reduce操作符为累加的意思*/
    val sum = (1..5).map { it * it }.reduce { a, b -> a + b  }
    println("sum:$sum")
}