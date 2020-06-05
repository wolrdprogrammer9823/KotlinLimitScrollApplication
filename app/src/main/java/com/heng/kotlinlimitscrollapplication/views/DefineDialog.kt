package com.heng.kotlinlimitscrollapplication.views
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.heng.kotlinlimitscrollapplication.R
import kotlinx.android.synthetic.main.define_dialog_layout.*

class DefineDialog : AlertDialog {

    private var mContext : Context? = null

    constructor(context: Context) : super(context, R.style.DefineDialogStyle) {
        mContext = context
    }

    constructor(context: Context, style: Int) : super(context, style) {
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.define_dialog_layout)
        btn_sure.setOnClickListener {
            this.dismiss()
        }

        btn_cancel.setOnClickListener {
            this.dismiss()
        }
    }

    override fun show() {
        super.show()
        setNavigationBarBgColor(android.R.color.white)
    }

    override fun dismiss() {
        super.dismiss()
        setNavigationBarBgColor(android.R.color.black)
    }

    private fun setNavigationBarBgColor(@ColorRes colorRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val activity = mContext as AppCompatActivity
            activity.window.navigationBarColor = mContext!!.resources.getColor(colorRes)
        }
    }
}