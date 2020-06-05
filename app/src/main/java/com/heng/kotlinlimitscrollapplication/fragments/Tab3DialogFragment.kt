package com.heng.kotlinlimitscrollapplication.fragments
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.util.doLog

class Tab3DialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab3_dialog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        /*全屏显示*/
        this.dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onActivityCreated(savedInstanceState)
        val dialogWindow = this.dialog?.window
        dialogWindow?.setBackgroundDrawable(ColorDrawable(0x00000000))
        dialogWindow?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
        doLog("override fun show(manager: FragmentManager, tag: String?)")
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        doLog("override fun onDismiss(dialog: DialogInterface)")
    }

}