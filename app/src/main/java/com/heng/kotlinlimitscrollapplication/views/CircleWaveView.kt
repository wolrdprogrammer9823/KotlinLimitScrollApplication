package com.heng.kotlinlimitscrollapplication.views
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import androidx.annotation.NonNull

const val duration = 2000L
const val speed = 500L

class CircleWaveView : View {

    private var initRadius : Float? = null
    private var maxRadius  : Float? = null

    private var lastCreatedTime : Long? = null

    private var circleList = ArrayList<Circle>()
    private var interpolator: Interpolator = LinearInterpolator()

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context,attributeSet: AttributeSet,defaultStyle:Int)
               : super(context,attributeSet,defaultStyle)

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
    }

    private inner class Circle{

        private var createdTime: Long = System.currentTimeMillis()

        constructor() {
            createdTime = System.currentTimeMillis()
        }

        public fun getAlpha() : Int{
            return 255.times(1.0F.minus(System.currentTimeMillis().minus(createdTime))).toInt()
        }

        public fun getCurrentRadius(): Float {
            return initRadius!!.plus(interpolator.getInterpolation(getPercent()).times(maxRadius!!.minus(initRadius!!)))
        }

        private fun getPercent(): Float {
            return System.currentTimeMillis().minus(createdTime).times(1.0F).div(duration)
        }
    }

    public fun setInterpolator(@NonNull interpolator: Interpolator) {
        this.interpolator = interpolator
    }
}