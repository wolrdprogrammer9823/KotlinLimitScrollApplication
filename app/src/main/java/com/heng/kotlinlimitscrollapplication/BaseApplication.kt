package com.heng.kotlinlimitscrollapplication
import android.app.Activity
import android.app.Application
import android.os.Bundle

open class BaseApplication : Application(),Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivityStopped(activity: Activity?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}
}