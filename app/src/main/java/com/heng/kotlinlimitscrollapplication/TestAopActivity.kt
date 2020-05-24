package com.heng.kotlinlimitscrollapplication
import activitystarter.Arg
import activitystarter.MakeActivityStarter
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.heng.kotlinlimitscrollapplication.aop.AopClickEvent
import com.heng.kotlinlimitscrollapplication.aop.CheckLogin
import com.heng.kotlinlimitscrollapplication.util.doLog
import com.marcinmoskala.activitystarter.argExtra
import kotlinx.android.synthetic.main.activity_test_aop.*

@MakeActivityStarter
class TestAopActivity : BaseActivity() {

    @get:Arg var name : String by argExtra()
    @get:Arg var phone : String by argExtra()
    @get:Arg(optional = true) var hobby : String by argExtra(defaultHobby)
    @get:Arg(optional = true) var mIsStudent : Boolean by argExtra(mNotStudent)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_aop)
        aop_value_one.text = "name:$name,phone:$phone,hobby:$hobby,isStudent:$mIsStudent"
    }

    override fun onContentChanged() {

        super.onContentChanged()
        test_aop.setOnClickListener {
            //checkLoginAspect?.mIsLogin = aop_value_et.text?.trim()?.equals("1")!!
            //success()
            aopClickEvent()
            //singleClickEvent()
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

    @AopClickEvent(1000)
    private fun aopClickEvent() {
        doLog("test_aop.setOnClickListener")
    }

    private fun singleClickEvent() {
        doLog("test_aop.SingleClickAspect")
    }

    companion object{
        const val defaultHobby = "游泳"
        const val mNotStudent = false
    }
}
