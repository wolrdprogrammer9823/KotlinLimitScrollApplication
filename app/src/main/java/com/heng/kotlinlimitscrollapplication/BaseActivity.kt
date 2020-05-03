package com.heng.kotlinlimitscrollapplication
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.heng.kotlinlimitscrollapplication.util.StatusBarUtil
import com.heng.swipebacklayout.BGASwipeBackHelper
import com.smarx.notchlib.INotchScreen
import com.smarx.notchlib.NotchScreenManager

abstract class BaseActivity : AppCompatActivity(),BGASwipeBackHelper.Delegate,View.OnClickListener {

    /*获取管理器*/
    private val notchScreenManager = NotchScreenManager.getInstance()

    private var mBGASwipeBackHelper: BGASwipeBackHelper? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setNavigationBarBgColor(android.R.color.black)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        initSwipeBackFinish()

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

    /*支持滑动返回*/
    override fun isSupportSwipeBack(): Boolean {
        /*默认支持滑动返回 不支持的话 重写返回false*/
        return true
    }

    /*正在滑动返回
    * slideOffset值从0到1
    * */
    override fun onSwipeBackLayoutSlide(slideOffset: Float) {

    }

    /*没有达到滑动返回的阈值  取消滑动返回操作  回到初始状态*/
    override fun onSwipeBackLayoutCancel() {

    }

    /*
    * 滑动执行完毕  销毁当前activity
    * */
    override fun onSwipeBackLayoutExecuted() {
        mBGASwipeBackHelper?.swipeBackward()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (mBGASwipeBackHelper?.isSliding!!) {
            /*正在滑动的时候 取消返回操作*/
            return
        }
        mBGASwipeBackHelper?.backward()
    }

    override fun onClick(v: View?) {}

    open fun statusBarColor() : Int {
        return 0xffff8800.toInt()
    }

    /*初始化滑动返回
    * 该方法 必须在onCreate方法的super.onCreate(savedInstanceState)之前调用
    * */
    private fun initSwipeBackFinish() {

        mBGASwipeBackHelper = BGASwipeBackHelper(this, this)

        /*设置滑动是否可用  默认为true*/
        mBGASwipeBackHelper?.setSwipeBackEnable(true)

        /*设置是否仅仅跟踪左侧滑动边缘的返回  默认值为true*/
        mBGASwipeBackHelper?.setIsOnlyTrackingLeftEdge(true)

        /*是否是微信的样式*/
        mBGASwipeBackHelper?.setIsWeChatStyle(true)

        /*是否需要显示阴影*/
        mBGASwipeBackHelper?.setIsNeedShowShadow(true)
        mBGASwipeBackHelper?.setShadowResId(R.drawable.bga_sbl_shadow)

        /*设置阴影区域的透明度是否跟随滑动距离逐渐变化 默认为true*/
        mBGASwipeBackHelper?.setIsShadowAlphaGradient(true)

        /*设置触发释放后自动返回的阈值 默认为0.3*/
        mBGASwipeBackHelper?.setSwipeBackThreshold(0.3F)

        /*设置底部导航栏是否悬浮在内容之上  默认为false*/
        mBGASwipeBackHelper?.setIsNavigationBarOverlap(false)
    }

    open fun setNavigationBarBgColor(@ColorRes colorRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            window.navigationBarColor = resources.getColor(colorRes)
        }
    }

    abstract fun adapterNotchForView(rect: Rect)
}
