package com.heng.kotlinlimitscrollapplication.util
import android.content.Context
import android.content.res.Configuration

object ScreenUtils {

    private fun getScreenOrientation(context: Context): Int {
        return context.resources.configuration.orientation
    }

    public fun screenIsLandscape(context: Context): Boolean {
        return getScreenOrientation(context) == Configuration.ORIENTATION_LANDSCAPE
    }

    public fun screenIsPortrait(context: Context): Boolean {
        return getScreenOrientation(context) == Configuration.ORIENTATION_PORTRAIT
    }
}