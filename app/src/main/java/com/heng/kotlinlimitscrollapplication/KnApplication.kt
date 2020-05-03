package com.heng.kotlinlimitscrollapplication
import android.app.Application
import com.heng.swipebacklayout.BGASwipeBackHelper

class KnApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /*初始化滑动返回库*/
        BGASwipeBackHelper.init(this, null)
    }

}