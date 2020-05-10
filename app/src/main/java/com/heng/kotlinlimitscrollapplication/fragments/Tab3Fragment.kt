package com.heng.kotlinlimitscrollapplication.fragments
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.TestAopActivity
import com.heng.kotlinlimitscrollapplication.adpater.Tab3Adapter
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
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

        val mIntent = Intent(requireContext(), TestAopActivity::class.java)
        startActivity(mIntent)
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
