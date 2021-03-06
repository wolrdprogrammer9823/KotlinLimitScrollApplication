package com.heng.kotlinlimitscrollapplication.fragments
import activitystarter.ActivityStarter
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.heng.kotlinlimitscrollapplication.DrawerActivityStarter
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.TestAopActivityStarter
import com.heng.kotlinlimitscrollapplication.adpater.Tab3Adapter
import com.heng.kotlinlimitscrollapplication.bean.Student
import com.heng.kotlinlimitscrollapplication.interfaces.IOnItemClickListener
import com.heng.kotlinlimitscrollapplication.presenter.GetDataPresenterImpl
import com.heng.kotlinlimitscrollapplication.presenter.IGetDataPresenter
import com.heng.kotlinlimitscrollapplication.util.doLog
import com.heng.kotlinlimitscrollapplication.view.IGetDataView
import com.heng.kotlinlimitscrollapplication.views.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_tab3.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab3Fragment : BaseLazyLoadFragment(), IOnItemClickListener<String>, IGetDataView {

    private var param1: String? = null
    private var param2: String? = null

    private var rvAdapter: Tab3Adapter? = null
    private var getDataPresenter: IGetDataPresenter? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab3Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        const val name = "疾风剑豪"
        const val phone = "15238954026"

        val student = Student("1121","green","七年级")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        ActivityStarter.fill(this, outState)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_tab3

    override fun onFragmentFirstVisible() {

        super.onFragmentFirstVisible()
        val space = 10
        val column = 3
        val layoutManager = StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL)
        /*解决item跳动问题*/
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        tab3_rv.layoutManager = layoutManager
        rvAdapter = Tab3Adapter(requireContext(), this)
        tab3_rv.addItemDecoration(GridItemDecoration(space, column))
        tab3_rv.addOnScrollListener(RvScrollListener(layoutManager))
        tab3_rv.adapter = rvAdapter

        getDataPresenter = GetDataPresenterImpl(this, "StaggeredGrid")
        getDataPresenter?.fetchData()
        //1.懒加载fragment  2.mvp模式  3.公共的事件监听   4.不居中显示问题
        //1.实配水滴屏   2.修改分割线颜色
        // 3.弹对话框删除item  4.添加icon和文本  5.涂鸦页面添加  6.极限回弹


        //https://github.com/bingoogolapple/BGASwipeBackLayout-Android  滑动返回上一activity
        //aop  事件点击

        //ActivityStarter 启动activity
        //熟练掌握 gradle_plugin_android_aspectjx
        // 通用场景:日志输出、方法计时、异步操作、异常拦截、动态权限
        // 业务场景:登录验证和单次点击

        //ViewPager2  DialogFragment
        //kotlin协程   view model + lifecycle
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        doLog(this.javaClass.simpleName + "override fun onFragmentResume()")
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        doLog(this.javaClass.simpleName + "override fun onFragmentPause()")
    }

    override fun getDataList(dataSet: List<String>) {
        rvAdapter?.setDataSet(dataSet)
    }

    override fun onItemClick(view: View, position: Int, content: String) {

//        val dialog = AlertDialog.Builder(requireContext())
//            .setMessage(content)
//            .setPositiveButton(R.string.sure, DialogInterface.OnClickListener{ dialog, _ ->
//                rvAdapter?.deleteItem(position)
//                dialog.dismiss()
//            })
//            .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener{ dialog, _ ->
//                dialog.dismiss()
//            })
//        dialog.create().show()
//        doLog("onItemClick")

        when(position) {
            1->{
                TestAopActivityStarter.start(requireActivity(), name, phone)
            }

            2->{
                DrawerActivityStarter.start(requireActivity(), student)
            }

            3->{
                childFragmentManager.let {
                    Tab3DialogFragment().show(it,"fragmentManager")
                }
            }
        }
    }

    class RvScrollListener(layoutManager: StaggeredGridLayoutManager) :
        RecyclerView.OnScrollListener() {
        private var layoutManager: StaggeredGridLayoutManager? = layoutManager
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            /*防止第一行有空白*/
            layoutManager?.invalidateSpanAssignments()
        }
    }

}
