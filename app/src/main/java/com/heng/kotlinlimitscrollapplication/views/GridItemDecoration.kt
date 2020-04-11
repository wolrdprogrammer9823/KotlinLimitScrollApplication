package com.heng.kotlinlimitscrollapplication.views
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration : RecyclerView.ItemDecoration {

    private var space: Int? = null
    private var column : Int? = null

    constructor(space: Int?, column: Int?) {
        this.space = space
        this.column = column
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        super.getItemOffsets(outRect, view, parent, state)
        /*获取item当前position*/
        val position = parent.getChildAdapterPosition(view)
        /*计算基本间距*/
        val baseSpace = space!!.plus(column!! + 1).div(column!!)
        /*计算列索引*/
        val columnIndex = position.rem(column!!)
        /*计算左右间距*/
        outRect.left = space!!.times(columnIndex + 1).minus(baseSpace.times(columnIndex))
        outRect.right = baseSpace.times(columnIndex + 1).minus(space!!.times(columnIndex + 1))

        if (position.rem(column!!) == column!!.minus(1)) {
            outRect.right = space!!
        }

        /*顶部边距*/
        outRect.top = space!!
    }
}