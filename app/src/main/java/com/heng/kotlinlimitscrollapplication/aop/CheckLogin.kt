package com.heng.kotlinlimitscrollapplication.aop

@Retention(AnnotationRetention.SOURCE) //存储在编译后的class文件  反射可见
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION)   //方法(不包括构造函数)
annotation class CheckLogin{

}