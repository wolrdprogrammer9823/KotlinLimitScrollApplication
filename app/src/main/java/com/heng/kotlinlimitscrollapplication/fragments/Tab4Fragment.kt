package com.heng.kotlinlimitscrollapplication.fragments
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.fragments.children.ChildOneFragment
import com.heng.kotlinlimitscrollapplication.fragments.children.ChildThreeFragment
import com.heng.kotlinlimitscrollapplication.fragments.children.ChildTwoFragment
import com.heng.kotlinlimitscrollapplication.util.doLog
import kotlinx.android.synthetic.main.fragment_tab4.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val CHILD_TAB_ONE = "childOneFragment"
private const val CHILD_TAB_TWO = "childTwoFragment"
private const val CHILD_TAB_THR = "childThreeFragment"

class Tab4Fragment : BaseLazyLoadFragment(), View.OnClickListener {

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

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        initData()
        dataInit = true
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        doLog(this.javaClass.simpleName + "->override fun onFragmentResume()")
        if (!dataInit) {
            initData()
            dataInit = true
        }
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        doLog(this.javaClass.simpleName + "->override fun onFragmentPause()")
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_format -> {
                if (currentIndex == 0) {
                    return
                }
                showFragment(0)
            }
            R.id.iv_insert -> {
                if (currentIndex == 1) {
                    return
                }
                showFragment(1)
            }
            R.id.iv_pie_chart -> {
                if (currentIndex == 2) {
                    return
                }
                showFragment(2)
            }
            else -> {
            }
        }
    }

    private fun showFragment(index: Int) {
        currentIndex = index
        val transaction = childFragmentManager.beginTransaction()
        resetIvBg(index)
        hideFragment(transaction)
        when (index) {
            0 -> {
                if (childOneFragment == null) {
                    childOneFragment = ChildOneFragment.newInstance(CHILD_TAB_ONE, CHILD_TAB_ONE)
                    transaction.add(R.id.fl_container, childOneFragment!!)
                } else {
                    transaction.show(childOneFragment!!)
                }
            }
            1 -> {
                if (childTwoFragment == null) {
                    childTwoFragment = ChildTwoFragment.newInstance(CHILD_TAB_TWO, CHILD_TAB_TWO)
                    transaction.add(R.id.fl_container, childTwoFragment!!)
                } else {
                    transaction.show(childTwoFragment!!)
                }
            }
            2 -> {
                if (childThreeFragment == null) {
                    childThreeFragment = ChildThreeFragment.newInstance(CHILD_TAB_THR, CHILD_TAB_THR)
                    transaction.add(R.id.fl_container, childThreeFragment!!)
                } else {
                    transaction.show(childThreeFragment!!)
                }
            }
            else -> {}
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (childOneFragment != null) {
            transaction.hide(childOneFragment!!)
        }
        if (childTwoFragment != null) {
            transaction.hide(childTwoFragment!!)
        }
        if (childThreeFragment != null) {
            transaction.hide(childThreeFragment!!)
        }
    }

    private fun resetIvBg(index: Int) {
        iv_format!!.setImageResource(R.drawable.ic_format_align_right_black_n)
        iv_format!!.setImageResource(R.drawable.ic_format_align_right_black_n)
        iv_insert!!.setImageResource(R.drawable.ic_insert_invitation_black_n)
        iv_pie_chart!!.setImageResource(R.drawable.ic_pie_chart_black_n)
        when (index) {
            0->{
                iv_format!!.setImageResource(R.drawable.ic_format_align_right_black_s)
            }
            1->{
                iv_insert!!.setImageResource(R.drawable.ic_insert_invitation_black_s)
            }
            2->{
                iv_pie_chart!!.setImageResource(R.drawable.ic_pie_chart_black_s)
            }
            else->{}
        }
    }

    /*初始化数据*/
    private fun initData() {
        iv_format?.setOnClickListener(this)
        iv_insert?.setOnClickListener(this)
        iv_pie_chart?.setOnClickListener(this)
        showFragment(0)
    }

    private var param1: String? = null
    private var param2: String? = null

    private var dataInit = false

    private var childOneFragment: ChildOneFragment? = null
    private var childTwoFragment: ChildTwoFragment? = null
    private var childThreeFragment: ChildThreeFragment? = null

    private var currentIndex = 0
}
