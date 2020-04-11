package com.heng.kotlinlimitscrollapplication.views
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class GridAppCompatTextView : AppCompatTextView {

    private var width: Int? = 0
    private var height :Int? = 0
    private var paddingLeft : Int? = 0
    private var paddingTop : Int? = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defaultStyle: Int) : super(context, attrs, defaultStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = MeasureSpec.getSize(widthMeasureSpec)
        height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width!!, width!!)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setPadding(width!!.shr(2), 0,0,0)
    }
}
