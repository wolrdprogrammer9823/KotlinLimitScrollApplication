package com.heng.kotlinlimitscrollapplication.aop

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class AopClickEvent(val clickTime : Long = 1000) {}