package com.heng.kotlinlimitscrollapplication.views
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class DefineItemDecoration(color: Int, orientation: Int) : ItemDecoration() {

    private val offset = 5F
    private var mOrientation = orientation
    private val mBounds = Rect()
    private val paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.strokeWidth = offset
        paint.color = color
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) {
            return
        }
        if (mOrientation == VERTICAL) {
            drawVertical(canvas, parent)
        } else {
            drawHorizontal(canvas, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {

        canvas.save()
//        val left: Int
//        val right: Int
//
//        if (parent.clipToPadding) {
//
//            left = parent.paddingLeft
//            right = parent.width - parent.paddingRight
//            canvas.clipRect(left, parent.paddingTop, right,
//                    parent.height - parent.paddingBottom)
//        } else {
//
//            left = 0
//            right = parent.width
//        }

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            canvas.drawRect(mBounds, paint)
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()

//        val top: Int
//        val bottom: Int
//        if (parent.clipToPadding) {
//            top = parent.paddingTop
//            bottom = parent.height - parent.paddingBottom
//            canvas.clipRect(parent.paddingLeft, top,
//                    parent.width - parent.paddingRight, bottom)
//        } else {
//            top = 0
//            bottom = parent.height
//        }

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
            canvas.drawRect(mBounds, paint)
        }
        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL) {
            outRect[0, 0, 0] = offset.toInt()
        } else {
            outRect[0, 0, offset.toInt()] = 0
        }
    }

    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL
        const val VERTICAL = LinearLayout.VERTICAL
    }

}