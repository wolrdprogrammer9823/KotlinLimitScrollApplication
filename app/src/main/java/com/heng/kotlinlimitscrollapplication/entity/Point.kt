package com.heng.kotlinlimitscrollapplication.entity

class Point {

    private var x = 0
    private var y = 0

    fun add() : Int{

        return 10.plus(x)
    }

    fun minus() : Int{

        return 5.minus(y)
    }
}