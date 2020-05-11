package com.heng.kotlinlimitscrollapplication.aopsingclick2
import android.os.SystemClock
import android.view.View
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.util.doLog
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

//const val ON_CLICK_POINTS = "execution(* android.view.View.OnClickListener.onClick(..))"
const val ON_CLICK_POINTS_IN_XML = "execution(* android.support.v7.app.AppCompatViewInflater.DeclaredOnClickListener.onClick(..))"
const val ON_CLICK_POINTS_IN_BUTTER_KNIFE = "execution(@butterknife.OnClick * *(..))"

@Aspect
class SingleClickAspect {

    //@Pointcut(ON_CLICK_POINTS)
    //fun onClickPoints(){}

    @Pointcut(ON_CLICK_POINTS_IN_XML)
    fun onClickPointsInXml(){}

    @Pointcut(ON_CLICK_POINTS_IN_BUTTER_KNIFE)
    fun onClickPointsInButterKnife() {}

    @Around("onClickPointsInXml() || onClickPointsInButterKnife()")
    @Throws(Throwable::class)
    fun aroundJointPoint(joinPoint: ProceedingJoinPoint) {

        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method

        //如果有Expect就不做防抖处理
        val hasExpectAnnotation = method != null && method.isAnnotationPresent(Expect::class.java)
        if (hasExpectAnnotation) {

            doLog("hasExpectAnnotation")
            joinPoint.proceed()
            return
        }

        val view = getViewFromArgs(joinPoint.args)
        if (view == null) {

            doLog("view == null")
            joinPoint.proceed()
            return
        }

        val lastClickTime = view.getTag(R.string.single_click_key) as Long
        if (lastClickTime == 0L) {

            doLog("lastClickTime == 0L")
            joinPoint.proceed()
            return
        }

        if (canClick(lastClickTime)) {

            doLog("canClick(lastClickTime)")
            joinPoint.proceed()
            return
        }

        doLog("can not click...")
    }


    private fun getViewFromArgs(args : Array<Any>?) : View?{
        if (args != null && args.isNotEmpty()) {
            val arg = args[0]
            if (arg is View) {
                return arg
            }
        }
        return null
    }

    private fun canClick(lastClickTime : Long)
            : Boolean = SystemClock.elapsedRealtime() - lastClickTime > 1000L
}