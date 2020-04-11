package com.heng.kotlinlimitscrollapplication.util

class DataSetUtil private constructor(){

    companion object{
        val instance : DataSetUtil by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DataSetUtil()
        }
    }

    fun createTab1Data(data: String): List<String> {

        val length = 51
        val listData = ArrayList<String>()

        for (i in 0.until(length)) {

            val data = data.plus(i + 1)
            listData.add(data)
        }

        return listData
    }
}