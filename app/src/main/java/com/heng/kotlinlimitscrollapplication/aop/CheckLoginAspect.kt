package com.heng.kotlinlimitscrollapplication.aop
import android.content.Context
import android.widget.Toast
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature

@Aspect //切面
open class CheckLoginAspect {

    var mIsLogin = true

    @Pointcut //切入点
    fun checkLogin() {}

    @Around("execution(@com.heng.kotlinlimitscrollapplication.aop.CheckLogin * *(..))") //环绕
    @Throws(Throwable::class) //可能抛出的异常
    fun aroundJointPoint(jointPoint: ProceedingJoinPoint) {

        val methodSignature = jointPoint.signature as MethodSignature
        val checkLogin = methodSignature.method.getAnnotation(CheckLogin::class.java)
        val context = jointPoint.`this` as Context
        if (checkLogin != null) {
            if (mIsLogin) {
                //满足条件  执行下一步逻辑
                jointPoint.proceed()
            } else {
                Toast.makeText(context,"请先登录哦",Toast.LENGTH_SHORT).show()
            }
        }
    }


}