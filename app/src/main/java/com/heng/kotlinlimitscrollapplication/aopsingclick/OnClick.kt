package com.heng.kotlinlimitscrollapplication.aopsingclick
import android.view.View

const val timeInterval = 1000L

inline fun View.setSafeListener(crossinline action:()->Unit){
    var lastClick = 0L
    setOnClickListener {
        val gap = System.currentTimeMillis() - lastClick
        lastClick=System.currentTimeMillis()
        if (gap < timeInterval) return@setOnClickListener
        action.invoke()
    }
}


var _viewClickFlag = false
var _clickRunnable = Runnable { _viewClickFlag = false }

fun View.click(action: (view: View) -> Unit) {
    setOnClickListener {
        if (!_viewClickFlag) {
            _viewClickFlag = true
            action(it)
        }
        removeCallbacks(_clickRunnable)
        postDelayed(_clickRunnable, 1000)
    }
}