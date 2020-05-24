package com.heng.kotlinlimitscrollapplication
import com.heng.kotlinlimitscrollapplication.util.doLog
import com.heng.swipebacklayout.BGASwipeBackHelper

class KnApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        /*初始化滑动返回库*/
        BGASwipeBackHelper.init(this, null)
        doLog("override fun onCreate()")
    }

}