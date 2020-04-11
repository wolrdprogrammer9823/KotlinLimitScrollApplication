package com.heng.kotlinlimitscrollapplication.util
import android.util.Log

fun doLog(message: String) {

    Log.d(LOG_LABEL, message)
}

const val LOG_LABEL = "log_label"