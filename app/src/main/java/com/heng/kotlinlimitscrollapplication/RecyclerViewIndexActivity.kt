package com.heng.kotlinlimitscrollapplication

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.heng.kotlinlimitscrollapplication.util.doLog

import kotlinx.android.synthetic.main.activity_recycler_view_index.*

class RecyclerViewIndexActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_index)
        doLog("override fun onCreate")
    }

    override fun onContentChanged() {

        super.onContentChanged()

        btn_go.setOnClickListener {
            val intent = Intent()
            intent.action = KnConstants.open_activity_action
            intent.`package` = KnConstants.app_package_name
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {

    }

    override fun adapterNotchForView(rect: Rect) {}
}
