package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..3).asFlow().map { request-> transformed(request) }
          .collect { response-> println(response) }
}

suspend fun transformed(request: Int): String {
    delay(100L)
    return "response $request"
}