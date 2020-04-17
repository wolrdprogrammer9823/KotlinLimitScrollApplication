package com.heng.kotlinlimitscrollapplication.views
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

const val radius = 300
const val rx = 500
const val ry = 400

class TestPorterDuffModeView : View {

    private var paint : Paint? = null
    private var portDuffMode : Xfermode? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    constructor(context: Context,attributeSet: AttributeSet,defaultStyle : Int) : super(context,attributeSet,defaultStyle) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        //canvas?.drawARGB(255, 3, 218, 197)
        canvas?.drawARGB(0, 0, 0, 0)
        val canvasW = width
        val canvasH = height
        val layerId = canvas?.saveLayer(0F, 0F, canvasW.toFloat(),
                                              canvasH.toFloat(), null, Canvas.ALL_SAVE_FLAG)

        canvas?.drawCircle(rx.toFloat(), ry.toFloat(), radius.toFloat(), paint!!)
        paint?.color = Color.YELLOW
        paint?.xfermode = portDuffMode
        canvas?.drawRect(rx.toFloat(), ry.toFloat(), rx.plus(400).toFloat(), ry.plus(520).toFloat(), paint!!)
        paint?.xfermode = null

        canvas?.restoreToCount(layerId!!)
    }

    private fun init() {

        paint = Paint()
        paint?.isAntiAlias = true
        paint?.isDither = true
        paint?.style = Paint.Style.FILL
        paint?.color = Color.MAGENTA

        portDuffMode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }
}