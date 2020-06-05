package com.heng.common_lib.singletons

/*双重检索单例*/
class SingletonTypeFour private constructor() {
    companion object {
        val instance: SingletonTypeFour by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonTypeFour()
        }
    }
}