package com.heng.kotlinlimitscrollapplication.widget
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.util.doLog

class IndexView : View {

    private val mData = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I",
        "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
        "W", "X", "Y", "Z")

    private var mSelectedIndex = -1
    private var mPaint: Paint? = null
    private var mTipTv: TextView? = null

    private var mPressedColor: Int? = null
    private var mNormalColor : Int? = null

    private var mDelegate : Delegate? = null

    constructor(context: Context) : super(context) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initData(context)
    }

    constructor(context: Context, attributeSet: AttributeSet,defaultStyle : Int)
                : super(context, attributeSet,defaultStyle) {
        initData(context)
    }

    override fun draw(canvas: Canvas?) {

        super.draw(canvas)

        val mWidth = width
        val singleHeight = height.div(mData.size)
        for (i in 0.until(mData.size)) {

            mPaint?.color = mNormalColor!!
            mPaint?.typeface = Typeface.DEFAULT
            if (i == mSelectedIndex) {
                mPaint?.color = mPressedColor!!
                mPaint?.typeface = Typeface.DEFAULT_BOLD
            }

            val xPos = mWidth.div(2) - mPaint?.measureText(mData[i])!!.div(2)
            val yPos = singleHeight.plus(singleHeight.times(i).toFloat())
            canvas?.drawText(mData[i], xPos, yPos, mPaint!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val newSelectIndex = (event?.y?.div(height)?.times(mData.size))?.toInt()
        when (event?.action) {
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                mTipTv?.visibility = INVISIBLE
                invalidate()
                mSelectedIndex = -1
            }
            else -> {
                if (newSelectIndex != null) {
                    if (newSelectIndex >= 0 && newSelectIndex < mData.size) {
                        if (newSelectIndex != mSelectedIndex) {

                            mDelegate?.onIndexViewSelectChanged(this, mData[newSelectIndex])

                            mTipTv?.visibility = VISIBLE
                            mTipTv?.text = mData[newSelectIndex]
                            mSelectedIndex = newSelectIndex
                        }
                    }
                }
            }
        }
        return true
    }

    private fun initData(context: Context) {

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.textSize = sp2px(14.0F)
        mPaint?.isDither = true
        mPaint?.typeface = Typeface.DEFAULT

        mPressedColor = context.resources.getColor(R.color.txt_normal_color)
        mNormalColor = context.resources.getColor(R.color.txt_pressed_color)
    }

    /*字体尺寸转换*/
    private fun sp2px(spValue : Float) : Float{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spValue,resources.displayMetrics)
    }

    fun setTipTextView(textView: TextView) {
        this.mTipTv = textView
    }

    fun setDelegate(delegate: Delegate) {
        this.mDelegate = delegate
    }

    interface Delegate {
        fun onIndexViewSelectChanged(indexView: IndexView, text: String)
    }
}