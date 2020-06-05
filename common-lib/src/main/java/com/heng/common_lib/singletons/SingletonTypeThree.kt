package com.heng.common_lib.singletons

/*线程安全的懒汉单例*/
class SingletonTypeThree private constructor(){
    companion object {
        private var instance: SingletonTypeThree? = null
            get() {
                if (field == null) {
                    field = SingletonTypeThree()
                }
                return field
            }

        @Synchronized
        fun get(): SingletonTypeThree {
            return instance!!
        }
    }
}