package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    foo14().collect { value -> println("value $value") }
}

fun foo14() : Flow<Int> = flow {
    println("Started to flow")
    withContext(Dispatchers.Default){
        for (i in 1..3) {
            Thread.sleep(100)
            emit(i)
        }
    }
}