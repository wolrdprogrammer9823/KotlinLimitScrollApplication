package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    threadLocal.set("main")
    println("Pre main: the current thread ${Thread.currentThread()},thread local value ${threadLocal.get()}")
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println("Launch started: the current thread ${Thread.currentThread()},thread local value ${threadLocal.get()}")
        yield()
        println("After yield: the current thread ${Thread.currentThread()},thread local value ${threadLocal.get()}")
    }
    job.join()
    println("Post main: the current thread ${Thread.currentThread()},thread local value ${threadLocal.get()}")
}

/*创建一个ThreadLocal对象*/
val threadLocal = ThreadLocal<String?>()