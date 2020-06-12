package com.heng.common_lib.singletons.coroutine_exception
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    supervisorScope {
        val child = launch(handler) {
            println("3421412421")
            throw AssertionError()
        }
        println("Scope is completing...")
    }

    println("Scope is completed...")
}