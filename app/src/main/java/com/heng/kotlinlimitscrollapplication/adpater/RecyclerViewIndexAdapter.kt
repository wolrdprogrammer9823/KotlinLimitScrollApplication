package com.heng.kotlinlimitscrollapplication.adpater
import androidx.recyclerview.widget.RecyclerView
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.bean.IndexModel

class RecyclerViewIndexAdapter : BGARecyclerViewAdapter<IndexModel> {

    constructor(recyclerView: RecyclerView) : super(recyclerView, R.layout.rv_index_item)

    override fun fillData(helper: BGAViewHolderHelper?, position: Int, model: IndexModel?) {
        helper?.setText(R.id.rv_index_item_tv, model?.name)
    }

    fun isCategory(position: Int): Boolean {
        val category = getItem(position).topCharacter?.get(0)
        return position == getPositionForCategory(category?.toInt()!!)
    }

    fun getPositionForCategory(category: Int): Int {
        for (i in 0.until(itemCount)) {
            val topCharacter = getItem(i).topCharacter
            val firstChar = (topCharacter?.toUpperCase()?.get(0))?.toInt()
            if (firstChar == category) {
                return i
            }
        }
        return -1
    }
}