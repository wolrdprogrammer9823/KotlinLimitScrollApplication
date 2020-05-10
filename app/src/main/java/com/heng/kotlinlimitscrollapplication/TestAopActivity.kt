package com.heng.kotlinlimitscrollapplication
import android.graphics.Rect
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.heng.kotlinlimitscrollapplication.aop.AopClickEvent
import com.heng.kotlinlimitscrollapplication.aop.CheckLogin
import com.heng.kotlinlimitscrollapplication.aop.CheckLoginAspect
import com.heng.kotlinlimitscrollapplication.aopsingclick.SingleClick
import com.heng.kotlinlimitscrollapplication.util.doLog
import kotlinx.android.synthetic.main.activity_test_aop.*

class TestAopActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_aop)
    }

    override fun onContentChanged() {

        super.onContentChanged()
        test_aop.setOnClickListener {
            //checkLoginAspect?.mIsLogin = aop_value_et.text?.trim()?.equals("1")!!
            //success()
            aopClickEvent()
        }
    }

    override fun adapterNotchForView(rect: Rect) {
        val mLayoutParams =
              aop_root_layout.layoutParams as FrameLayout.LayoutParams
        mLayoutParams.topMargin = rect.bottom
        aop_root_layout.layoutParams = mLayoutParams
    }

    @CheckLogin
    private fun success() {
        Toast.makeText(this,"跳转成功!!!",Toast.LENGTH_SHORT).show()
    }

    @SingleClick(2000)
    private fun aopClickEvent() {
        doLog("test_aop.setOnClickListener")
    }
}
