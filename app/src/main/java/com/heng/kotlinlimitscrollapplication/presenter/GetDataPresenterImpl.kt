package com.heng.kotlinlimitscrollapplication.presenter
import com.heng.kotlinlimitscrollapplication.model.GetDataModelImpl
import com.heng.kotlinlimitscrollapplication.view.IGetDataView

class GetDataPresenterImpl(dataSetView: IGetDataView, type: String) : IGetDataPresenter {

    private val mType = type
    private val mDataSetView = dataSetView
    private val mDataModel = GetDataModelImpl(mType)

    override fun fetchData() {

        mDataSetView.getDataList(mDataModel.getDataSet())
    }
}