package com.heng.kotlinlimitscrollapplication.views
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.TranslateAnimation
import android.widget.ScrollView
import kotlin.math.abs

const val animationTime = 400L

class LimitScrollView : ScrollView {

    private var contentView: View? = null
    private val rect = Rect()
    private var downY = 0
    private var moveY = 0
    private var offsetY = 0

    private var mTouchSlop = 0

    constructor(context : Context) : super(context) {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {}

    override fun onFinishInflate() {
        super.onFinishInflate()
        contentView = getChildAt(0)
        if (contentView == null) {
            return
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        rect.left = l
        rect.top = t
        rect.right = r
        rect.bottom = b
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {

            MotionEvent.ACTION_DOWN -> {

                downY = ev.y.toInt()
            }

            MotionEvent.ACTION_MOVE -> {

                offsetY = (ev.y - downY).toInt()
                contentView?.layout(
                    rect.left, rect.top + offsetY.times(0.5F).toInt(),
                    rect.right, rect.bottom + offsetY.times(0.5F).toInt()
                )
            }

            MotionEvent.ACTION_UP -> {

                val translation = contentView?.top?.toFloat()?.let {
                    TranslateAnimation(0F, 0F, it, rect.top.toFloat())}
                translation?.duration = animationTime
                contentView?.startAnimation(translation!!)

                contentView?.layout(rect.left, rect.top, rect.right, rect.bottom)
            }
            else -> {}
        }
        return super.dispatchTouchEvent(ev)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN->{
                downY = ev.y.toInt()
            }
            MotionEvent.ACTION_MOVE->{
                moveY = ev.y.toInt()
                if (abs(moveY - downY) > mTouchSlop) {
                    return true
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
}