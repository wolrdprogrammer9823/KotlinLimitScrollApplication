package com.heng.common_lib.singletons.coroutine_context_dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@ObsoleteCoroutinesApi
fun main() {
    newSingleThreadContext("ctx1").use { ctx1->
        newSingleThreadContext("ctx2").use { ctx2->{
            runBlocking(ctx1) {

                doLog("Started in ctx1")

                withContext(ctx2){
                    doLog("Working in ctx2")
                }

                doLog("Back to ctx1")
            }
          }
        }
    }
}

fun doLog(msg: String) = println("${Thread.currentThread().name}->$msg")