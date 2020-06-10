package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    /*try-finally作为操作结束符*/
    try {
        (1..3).asFlow().collect { value -> println(value) }
    } finally {
        println("Done!!!")
    }
}