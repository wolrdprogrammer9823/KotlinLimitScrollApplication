package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..3).asFlow().transform { request-> emit("Marking request $request")
                                          emit(performRequest(request))}
         .collect { value-> println(value) }
}

suspend fun performRequest(request: Int): String {
    delay(100L)
    return "response $request"
}