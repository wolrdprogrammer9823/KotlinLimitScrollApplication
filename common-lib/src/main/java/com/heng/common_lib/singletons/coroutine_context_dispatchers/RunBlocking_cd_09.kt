package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    launch(Dispatchers.Default + CoroutineName("name 1")) {
        println("I am working in ${Thread.currentThread().name}")
    }
}