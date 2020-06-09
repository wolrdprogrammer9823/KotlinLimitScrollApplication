package com.heng.common_lib.singletons.coroutine_flow

fun main() {
    foo2().forEach { value-> println(value) }
}

fun foo2(): Sequence<Int> = sequence {
    for (i in 0..3) {
        Thread.sleep(1000L)
        yield(i)
    }
}