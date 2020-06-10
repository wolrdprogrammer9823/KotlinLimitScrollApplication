package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@FlowPreview
fun main() = runBlocking {

    val startTime = System.currentTimeMillis()
    (1..3).asFlow()
          .onEach { delay(100L) }
          .flatMapConcat { value -> requestFlow(value) }
          .collect {value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start.")
          }
}

fun requestFlow(i : Int) : Flow<String> = flow {
    emit("$i:first")
    delay(500L)
    emit("$i:second")
}