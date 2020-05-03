package com.heng.kotlinlimitscrollapplication.fragments
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.RecyclerViewIndexActivity
import com.heng.kotlinlimitscrollapplication.adpater.Tab2Adapter
import com.heng.kotlinlimitscrollapplication.interfaces.IOnItemClickListener
import com.heng.kotlinlimitscrollapplication.presenter.GetDataPresenterImpl
import com.heng.kotlinlimitscrollapplication.presenter.IGetDataPresenter
import com.heng.kotlinlimitscrollapplication.util.DataSetUtil
import com.heng.kotlinlimitscrollapplication.view.IGetDataView
import com.heng.kotlinlimitscrollapplication.views.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_tab2.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tab2Fragment : BaseLazyLoadFragment(), IOnItemClickListener<String>, IGetDataView {

    private var param1: String? = null
    private var param2: String? = null

    private var rvAdapter: Tab2Adapter? = null
    private var getDataPresenter: IGetDataPresenter? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab2Fragment().apply {
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

    override fun getLayoutRes(): Int = R.layout.fragment_tab2

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()

        val space = 5
        val column = 3
        val layoutManager = GridLayoutManager(requireContext(), column)
        rvAdapter = Tab2Adapter(requireContext(), this)
        tab2_rv.layoutManager = layoutManager
        tab2_rv.addItemDecoration(GridItemDecoration(space, column))
        tab2_rv.adapter = rvAdapter

        getDataPresenter = GetDataPresenterImpl(this, "gridView")
        getDataPresenter?.fetchData()
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
        //Toast.makeText(requireContext(), content, Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), RecyclerViewIndexActivity::class.java)
        startActivity(intent)
    }
}
