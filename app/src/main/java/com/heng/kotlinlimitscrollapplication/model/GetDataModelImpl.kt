package com.heng.kotlinlimitscrollapplication.model
import com.heng.kotlinlimitscrollapplication.util.DataSetUtil

class GetDataModelImpl(private val type: String) : IGetDataModel {

    override fun getDataSet(): List<String> {
        return DataSetUtil.instance.createTab1Data(type)
    }
}