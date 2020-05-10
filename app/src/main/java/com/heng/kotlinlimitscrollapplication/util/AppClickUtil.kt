package com.heng.kotlinlimitscrollapplication.util
import android.os.SystemClock

object AppClickUtil {

    var mLastClickTime = 0L

    fun mIsFastDoubleClick(intervalTimes : Long) : Boolean {

        val currentTime = SystemClock.elapsedRealtime()
        val timeDifference = kotlin.math.abs(currentTime - mLastClickTime)
        doLog("timeIntervals:${timeDifference}")
        return if (timeDifference < intervalTimes) { true
        } else {
            mLastClickTime = currentTime
            false
        }
    }
}