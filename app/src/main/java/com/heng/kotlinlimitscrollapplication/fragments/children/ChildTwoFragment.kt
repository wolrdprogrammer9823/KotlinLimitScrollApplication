package com.heng.kotlinlimitscrollapplication.fragments.children
import android.os.Bundle
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.fragments.BaseLazyLoadFragment

class ChildTwoFragment private constructor() : BaseLazyLoadFragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            mParam1 = it!!.getString(ARG_PARAM1)
            mParam2 = it!!.getString(ARG_PARAM2)
        }
    }

    companion object {

        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        @JvmStatic
        fun newInstance(param1: String?, param2: String?): ChildTwoFragment {
            val fragment = ChildTwoFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_child_two
}
