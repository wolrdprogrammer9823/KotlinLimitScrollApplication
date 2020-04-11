package com.heng.kotlinlimitscrollapplication
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import com.heng.kotlinlimitscrollapplication.util.StatusBarUtil
import com.heng.kotlinlimitscrollapplication.util.doLog
import com.smarx.notchlib.INotchScreen
import com.smarx.notchlib.NotchScreenManager

abstract class BaseActivity : AppCompatActivity() {

    /*获取管理器*/
    private val notchScreenManager = NotchScreenManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        /*适配沉浸式状态栏*/
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setTranslucentStatus(this)
        StatusBarUtil.setStatusBarColor(this,statusBarColor())
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            /*不支持暗黑模式情况下  状态栏背景为白色  会看不清字体  所以设置状态栏颜色为半透明*/
            StatusBarUtil.setStatusBarColor(this,0x55000000.toInt())
        }

        /*适配水滴屏 刘海屏*/
        /*设置全屏显示*/
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        /*隐藏ActionBar*/
        supportActionBar?.hide()
        /*支持显示到刘海区域*/
        notchScreenManager.setDisplayInNotch(this)
        /*获取刘海区域信息*/
        notchScreenManager.getNotchInfo(this,INotchScreen.NotchScreenCallback { notchScreenInfo ->
            if (notchScreenInfo.hasNotch) {
                for (rect in notchScreenInfo.notchRects) {
                    adapterNotchForView(rect)
                }
            }
        } )

    }

    open fun statusBarColor() : Int {
        return 0xffff8800.toInt()
    }

    abstract fun adapterNotchForView(rect: Rect)
}
