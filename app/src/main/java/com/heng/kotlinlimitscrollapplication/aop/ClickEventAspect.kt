package com.heng.kotlinlimitscrollapplication.aop
import com.heng.kotlinlimitscrollapplication.util.AppClickUtil
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

const val pointCut = "execution(@com.heng.kotlinlimitscrollapplication.aop.AopClickEvent * *(..))"

@Aspect
class ClickEventAspect {

    @Pointcut(pointCut)
    fun checkClickEvent(){}

    @Around("checkClickEvent()")
    @Throws(Throwable::class)
    fun dealWithEvent(joinPoint: ProceedingJoinPoint) {

        val methodSignature = joinPoint.signature as MethodSignature
        //获取方法
        val method = methodSignature.method
        if (!method.isAnnotationPresent(AopClickEvent::class.java)) {
            return
        }

        val aopClick = method.getAnnotation(AopClickEvent::class.java)
        //判断是否快速点击
        if (!AppClickUtil.mIsFastDoubleClick(aopClick.clickTime)) {
            //不是快速点击 执行方法
            joinPoint.proceed()
        }
    }
}