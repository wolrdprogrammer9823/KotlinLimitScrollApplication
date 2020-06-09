package com.heng.common_lib.singletons.coroutine_flow

fun main() {
    foo().forEach {value-> println(value)}
}

fun foo(): List<Int> = listOf(1, 2, 3)