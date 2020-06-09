package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    foo3().forEach { value-> println(value) }
}

suspend fun foo3() : List<Int> {
    delay(1000L) //模拟耗时场景
    return listOf(1, 2, 3, 4, 5)
}