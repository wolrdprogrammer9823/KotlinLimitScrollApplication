package com.heng.common_lib.singletons

/*静态内部类单例*/
class SingletonTypeFive {

    companion object {
        val instance = SingletonHolder.instance
    }

    private object SingletonHolder{
        val instance = SingletonTypeFive()
    }
}