package com.heng.kotlinlimitscrollapplication.fragments
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.heng.kotlinlimitscrollapplication.MainActivity
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.adpater.Tab1Adapter
import com.heng.kotlinlimitscrollapplication.interfaces.IOnItemClickListener
import com.heng.kotlinlimitscrollapplication.presenter.GetDataPresenterImpl
import com.heng.kotlinlimitscrollapplication.presenter.IGetDataPresenter
import com.heng.kotlinlimitscrollapplication.util.doLog
import com.heng.kotlinlimitscrollapplication.view.IGetDataView
import com.heng.kotlinlimitscrollapplication.views.DefineDialog
import com.heng.kotlinlimitscrollapplication.views.DefineItemDecoration
import kotlinx.android.synthetic.main.fragment_tab1.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab1Fragment : BaseLazyLoadFragment(), IOnItemClickListener<String>, IGetDataView {

    private var param1: String? = null
    private var param2: String? = null
    private var mContext : Context? = null
    private var rvAdapter: Tab1Adapter? = null
    private var getDataPresenter: IGetDataPresenter? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_tab1

    override fun onFragmentFirstVisible() {

        super.onFragmentFirstVisible()
        val layoutManager = LinearLayoutManager(requireContext())
        rvAdapter = Tab1Adapter(requireContext(), this)
        tab1_rv.layoutManager = layoutManager
        val itemDecoration = DefineItemDecoration(0xff6200EE.toInt(), DefineItemDecoration.VERTICAL)
        tab1_rv.addItemDecoration(itemDecoration)
        tab1_rv.adapter = rvAdapter

        getDataPresenter = GetDataPresenterImpl(this, "data")
        getDataPresenter?.fetchData()
    }

    override fun onFragmentResume() {
        super.onFragmentResume()
        doLog(this.javaClass.simpleName + "->override fun onFragmentResume()")
    }

    override fun onFragmentPause() {
        super.onFragmentPause()
        doLog(this.javaClass.simpleName + "->override fun onFragmentPause()")
    }

    override fun getDataList(dataSet: List<String>) {
        rvAdapter?.setDataSet(dataSet)
    }

    override fun onItemClick(view: View, position: Int, content: String) {
        //Toast.makeText(requireContext(), content, Toast.LENGTH_SHORT).show()
        val dialog = DefineDialog(requireContext())
//            .setMessage(content)
//            .setPositiveButton(R.string.sure, DialogInterface.OnClickListener{ dialog, _ ->
//
//                rvAdapter?.deleteItem(position)
//                dialog.dismiss()
//            })
//            .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener{ dialog, _ ->
//                dialog.dismiss()
//            })
       dialog.show()
        doLog("onItemClick")
        //val mainActivity = mContext as MainActivity
        //mainActivity.setNavigationBarBgColor(android.R.color.white)
        //rvAdapter!!.notifyDataSetChanged()
    }
}
