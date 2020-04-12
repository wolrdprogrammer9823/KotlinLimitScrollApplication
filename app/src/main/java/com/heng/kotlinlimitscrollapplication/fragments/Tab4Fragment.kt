package com.heng.kotlinlimitscrollapplication.fragments
import android.os.Bundle
import com.heng.kotlinlimitscrollapplication.R
import kotlinx.android.synthetic.main.fragment_tab4.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab4Fragment : BaseLazyLoadFragment() {

    private var param1: String? = null
    private var param2: String? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab4Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_tab4

    override fun onFragmentResume() {
        super.onFragmentResume()
        expanded_cv.resumeExpand()
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        expanded_cv.stopExpand()
    }
}
