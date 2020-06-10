package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun main() = runBlocking {

    /*onEach{}块与catch{}配合可以捕获所有的异常*/
    byFlow().onEach { value->
                      check(value <= 1) {"collected $value"}
                      println("value $value")
                     }
            .catch { e -> println("caught $e") }
            .collect()

}

fun byFlow() : Flow<Int> = flow {
    for (i in 1..3) {
        println("emit $i")
        emit(i)
    }
}