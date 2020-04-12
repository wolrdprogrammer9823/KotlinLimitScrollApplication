package com.heng.kotlinlimitscrollapplication.views
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import com.heng.kotlinlimitscrollapplication.util.ScreenUtils

const val expand_radius_message = 0x10

class ExpandCircleView : View {

    private var viewWidth = 0
    private var viewHeight = 0
    private var circleRadius = 0F
    private var maxRadius = 0
    private var startRadius = 0F
    private var originalRadius = 0F

    private var paint: Paint? = null
    private var handler : ViewHandler? = null

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defaultStyle: Int)
               : super(context,attributeSet,defaultStyle){
        init()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewWidth = MeasureSpec.getSize(widthMeasureSpec)
        viewHeight = MeasureSpec.getSize(heightMeasureSpec)

        circleRadius = if (ScreenUtils.screenIsPortrait(context)){
            viewWidth.div(4).toFloat()
        } else{
            viewHeight.div(4).toFloat()
        }
        startRadius = circleRadius
        originalRadius = circleRadius
        maxRadius = circleRadius.toInt().plus(50)
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        val cx = viewWidth.div(2).toFloat()
        val cy = viewHeight.div(2).toFloat()
        canvas?.drawCircle(cx, cy, circleRadius, paint!!)
        canvas?.drawCircle(cx, cy, startRadius, paint!!)
    }

    private fun init() {

        paint = Paint()
        paint?.isAntiAlias = true
        paint?.isDither = true
        paint?.color = Color.MAGENTA
        paint?.strokeWidth = 5f
        paint?.style = Paint.Style.STROKE

        handler = ViewHandler()
    }

    fun stopExpand() {
        handler?.removeMessages(expand_radius_message)
    }

    fun resumeExpand() {
        handler?.sendEmptyMessageDelayed(expand_radius_message,1000L)
    }

    inner class ViewHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                expand_radius_message->{
                    startRadius = startRadius.plus(5)
                    if (startRadius.toInt() > maxRadius) {
                        startRadius = originalRadius
                    }
                    invalidate()
                    resumeExpand()
                } else->{}
            }
        }
    }

}