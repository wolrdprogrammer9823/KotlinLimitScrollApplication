package com.heng.kotlinlimitscrollapplication.interfaces
import android.view.View

interface IOnItemClickListener<Data> {

    fun onItemClick(view: View, position: Int, data: Data)
}