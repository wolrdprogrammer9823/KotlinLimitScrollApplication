package com.heng.common_lib.singletons.coroutine_flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    (1..5).asFlow().filter {
        println("filter $it")
        it % 2 == 0
    }.map {
        println("map $it")
        "string $it"
    }.collect { value ->println("collect $value") }
}