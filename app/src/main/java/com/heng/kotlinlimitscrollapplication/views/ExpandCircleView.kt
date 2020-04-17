package com.heng.kotlinlimitscrollapplication.views
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.heng.kotlinlimitscrollapplication.util.ScreenUtils
import java.lang.ref.WeakReference

const val expand_radius_message = 0x10

class ExpandCircleView : View {

    private var viewWidth = 0
    private var viewHeight = 0
    private var circleRadius = 0F
    private var maxRadius = 0
    private var startRadius = 0F
    private var originalRadius = 0F

    /*波浪高度*/
    private var waveHeight = 0F
    /*波浪长度*/
    private var waveDx = 0F
    /*x轴偏移量*/
    private var dx = 0

    private var rectF: RectF? = null

    private var path: Path? = null
    private var paint: Paint? = null
    private var wavePaint: Paint? = null
    private var bmpPaint : Paint? = null

    private var bitmap : Bitmap? = null
    private var bmpCanvas : Canvas? = null
    private var porterDuffMode : Xfermode? = null

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

        //waveDx = viewWidth.toFloat()
        waveDx = circleRadius.times(2)
        waveHeight = circleRadius.times(2).div(3)

    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        val cx = viewWidth.div(2).toFloat()
        val cy = viewHeight.div(2).toFloat()

        canvas?.drawARGB(0, 0, 0, 0)

        val canvasW = width
        val canvasH = height
        val layerId = canvas?.saveLayer(0F, 0F, canvasW.toFloat(),
                                             canvasH.toFloat(), null,Canvas.ALL_SAVE_FLAG)

        canvas?.drawCircle(cx, cy, circleRadius, paint!!)
        canvas?.drawCircle(cx, cy, startRadius, paint!!)

        paint?.color = Color.YELLOW
        paint?.style = Paint.Style.FILL
        paint?.xfermode = porterDuffMode

        /*绘制波浪*/
        drawWave(canvas)

        canvas?.restoreToCount(layerId!!)
    }

    private fun init() {

        porterDuffMode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        paint = Paint()
        paint?.isAntiAlias = true
        paint?.isDither = true
        paint?.color = Color.MAGENTA
        paint?.strokeWidth = 5f
        paint?.style = Paint.Style.STROKE

        path = Path()
        rectF = RectF()

        wavePaint = Paint()
        wavePaint?.isAntiAlias = true
        wavePaint?.isDither = true
        wavePaint?.color = Color.YELLOW
        wavePaint?.strokeWidth = 2f
        wavePaint?.style = Paint.Style.FILL

        bmpPaint = Paint()
        bmpPaint?.isAntiAlias = true
        bmpPaint?.color = Color.TRANSPARENT
        bmpPaint?.style = Paint.Style.FILL
        bmpPaint?.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        bmpCanvas = Canvas()

        handler = ViewHandler(this)
    }

    private fun drawWave(canvas: Canvas?) {

        path?.reset()
        //path?.moveTo(-waveDx.plus(dx), viewHeight.toFloat().div(2))
        //path?.moveTo(viewWidth.div(2).minus(circleRadius).plus(dx), viewHeight.toFloat().div(2))
        //for (i in (-waveDx.toInt()).until(circleRadius.toInt().times(2).plus(waveDx.toInt())) step waveDx.toInt()) {
        //    path?.rQuadTo(waveDx.div(4), -waveHeight.div(2), waveDx.div(2), 0F)
        //    path?.rQuadTo(waveDx.div(4), waveHeight.div(2), waveDx.div(2), 0F)
        //}

        //path?.lineTo(viewWidth.div(2).plus(circleRadius), viewHeight.div(2).toFloat())
        rectF?.set(viewWidth.div(2).minus(circleRadius),viewHeight.div(2).toFloat(),
                   viewWidth.div(2).plus(circleRadius),viewHeight.div(2).plus(circleRadius))

        //path?.addArc(rectF, 0F, 180F)
        //path?.arcTo(rectF, 0F, 180F)
        //path?.lineTo(0F,viewHeight.toFloat())

        path?.addRect(rectF, Path.Direction.CW)

        //path?.close()
        canvas?.drawPath(path!!, paint!!)
    }

    private fun drawWave2(canvas: Canvas?) {
        waveDx = viewWidth.toFloat()
        path?.reset()
        path?.moveTo(-waveDx.plus(dx), viewHeight.toFloat().div(2) )
        for (i in (-waveDx.toInt()).until(viewWidth.plus(waveDx.toInt())) step waveDx.toInt()) {
            path?.rQuadTo(waveDx.div(4), -waveHeight.div(2), waveDx.div(2), 0F)
            path?.rQuadTo(waveDx.div(4), waveHeight.div(2), waveDx.div(2), 0F)
        }

        path?.lineTo(viewWidth.toFloat(), viewHeight.toFloat())
        path?.lineTo(0F,viewHeight.toFloat())
        path?.close()
        canvas?.drawPath(path!!, wavePaint!!)
    }

    fun stopExpand() {
        handler?.removeMessages(expand_radius_message)
    }

    fun resumeExpand() {
        handler?.sendEmptyMessageDelayed(expand_radius_message,1000L)
    }

    fun startAnimation() {
        val valueAnimator =  ValueAnimator.ofFloat(0F, waveDx)
        valueAnimator.duration = 2000L
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener {
            dx = it.animatedValue.toString().toFloat().toInt()
            invalidate()
        }
        valueAnimator.start()
    }

    class ViewHandler(view: ExpandCircleView) : Handler() {

        private val weakReference = WeakReference<ExpandCircleView>(view)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val weakView = weakReference.get()
            when (msg?.what) {
                expand_radius_message->{
                    weakView?.startRadius = weakView?.startRadius!!.plus(5)
                    if (weakView.startRadius.toInt() > weakView.maxRadius) {
                        weakView.startRadius = weakView.originalRadius
                    }
                    weakView.invalidate()
                    weakView.resumeExpand()
                } else->{}
            }
        }
    }

}