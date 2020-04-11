package com.heng.kotlinlimitscrollapplication.views
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class GridAppCompatImageView : AppCompatImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context,attrs: AttributeSet,defaultStyle : Int) : super(context,attrs,defaultStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, width)
    }
}