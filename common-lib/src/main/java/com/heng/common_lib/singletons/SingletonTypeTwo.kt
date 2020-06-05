package com.heng.common_lib.singletons

class SingletonTypeTwo private constructor(){

    /*懒汉模式--单例*/
    companion object{
        private var singletonTypeTwo: SingletonTypeTwo? = null
        get() {
            if (field == null) {
                field = SingletonTypeTwo()
            }
            return field
        }
        fun get(): SingletonTypeTwo {
            return singletonTypeTwo!!
        }
    }
}