package com.heng.kotlinlimitscrollapplication.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseLazyLoadFragment : Fragment() {

    private var rootView : View? = null
    private var mIsFirstVisible = true
    private var mIsViewCreated = false
    private var currentVisibleState = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(),container,false)
        }
        //initView(rootView!!)
        return rootView
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (mIsViewCreated) {
            if (isVisibleToUser && !currentVisibleState) {
                dispatchChildVisibleHint(true)
            } else if (!isVisibleToUser && currentVisibleState) {
                dispatchChildVisibleHint(false)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mIsViewCreated = true
        if (!isHidden && userVisibleHint) {
            dispatchChildVisibleHint(true)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            dispatchChildVisibleHint(false)
        } else {
            dispatchChildVisibleHint(true)
        }
    }

    override fun onResume() {
        super.onResume()
        if (!mIsFirstVisible) {
            if (!isHidden && !currentVisibleState && userVisibleHint) {
                dispatchChildVisibleHint(true)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (currentVisibleState && userVisibleHint) {
            dispatchChildVisibleHint(false)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mIsViewCreated = false
        mIsFirstVisible = false
    }

    open fun onFragmentFirstVisible() {}

    open fun onFragmentResume() {}

    open fun onFragmentPause() {}

    /*
    * 统一处理  显示隐藏
    * */
    private fun dispatchChildVisibleHint(visible: Boolean) {
        if (visible && isParentVisible()) {
            return
        }

        if (currentVisibleState == visible) {
            return
        }

        currentVisibleState = visible

        if (visible) {
            if (mIsFirstVisible) {

                mIsFirstVisible = false
                onFragmentFirstVisible()
            }

            onFragmentResume()
            dispatchChildVisibleState(true)
        } else {

            onFragmentPause()
            dispatchChildVisibleState(false)
        }
    }

    /*用于分发可见时间的时候判断父Fragment是否可见*/
    /*
    * true 不可见  false可见
    * */
    private fun isParentVisible() : Boolean {
        if (parentFragment == null) {
            return false
        }

        val baseLazyLoadFragment = parentFragment as BaseLazyLoadFragment
        return baseLazyLoadFragment.isSupportVisible()
    }

    private fun dispatchChildVisibleState(visible: Boolean) {
       val mFragmentManager =  fragmentManager
       val fragmentList : List<Fragment> = mFragmentManager!!.fragments
        if (fragmentList.isNotEmpty()) {
            for (child : Fragment in fragmentList) {
                if (child is BaseLazyLoadFragment && !child.isHidden && child.userVisibleHint) {
                    dispatchChildVisibleHint(visible)
                }
            }
        }
    }

    private fun isSupportVisible(): Boolean {
        return currentVisibleState
    }

    private fun isFragmentVisible(fragment: Fragment): Boolean {
        return !isHidden && fragment.userVisibleHint
    }

    abstract fun getLayoutRes() : Int

    //public abstract fun initView(rootView: View)
}