package com.heng.kotlinlimitscrollapplication
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import cn.bingoogolapple.baseadapter.BGADivider
import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener
import cn.bingoogolapple.baseadapter.BGAOnRVItemClickListener
import cn.bingoogolapple.baseadapter.BGARVVerticalScrollHelper
import com.heng.kotlinlimitscrollapplication.adpater.RecyclerViewIndexAdapter
import com.heng.kotlinlimitscrollapplication.util.DataSetUtil
import com.heng.kotlinlimitscrollapplication.util.doLog
import com.heng.kotlinlimitscrollapplication.widget.IndexView
import kotlinx.android.synthetic.main.activity_recycler_view_index.*

class RecyclerViewIndexActivity : BaseActivity(), BGAOnRVItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_index)
    }

    override fun onContentChanged() {

        super.onContentChanged()

        initToolBar()

        mRvAdapter = RecyclerViewIndexAdapter(rv_rv_data)
        mRvAdapter?.data = DataSetUtil.instance.loadIndexModelData()

        /*设置事件*/
        setListener()

        rv_rv_data.addItemDecoration(
            BGADivider.newBitmapDivider()
                      .setStartSkipCount(0)
                      .setMarginLeftResource(R.dimen.dp_12)
                      .setMarginRightResource(R.dimen.dp_20)
                      .setDelegate(bgaDelegate!!))
        rv_rv_data.adapter = mRvAdapter

        rv_index_view.setTipTextView(rv_tip_tv)
    }

    override fun adapterNotchForView(rect: Rect) {
        val mLayoutParams =
            rv_toolbar.layoutParams as LinearLayoutCompat.LayoutParams
        mLayoutParams.topMargin = rect.bottom
        rv_toolbar.layoutParams = mLayoutParams
    }

    /*RecyclerView item事件*/
    override fun onRVItemClick(parent: ViewGroup?, itemView: View?, position: Int) {
        Toast.makeText(this,"选择了城市:${mRvAdapter?.getItem(position)?.name}",
                        Toast.LENGTH_SHORT).show()
    }

    private fun setListener() {

        mRvAdapter?.setOnRVItemClickListener(this)

        bgaDelegate = object : BGADivider.StickyDelegate() {
            override fun getFirstVisibleItemPosition(): Int {
                return mScrollHelper!!.findFirstVisibleItemPosition()
            }

            override fun isCategoryFistItem(position: Int): Boolean {
                return mRvAdapter?.isCategory(position)!!
            }

            override fun getCategoryName(position: Int): String {
                return mRvAdapter?.getItem(position)?.topCharacter!!
            }
        }

        mScrollHelper = BGARVVerticalScrollHelper.newInstance(rv_rv_data,
            object : BGARVVerticalScrollHelper.SimpleDelegate(){
            override fun getCategoryHeight(): Int {
                return bgaDelegate!!.categoryHeight
            }
        })

        rv_index_view.setDelegate(object : IndexView.Delegate {
            override fun onIndexViewSelectChanged(indexView: IndexView, text: String) {
                val position = mRvAdapter?.getPositionForCategory(text[0].toInt())
                if (position != -1) {
                    mScrollHelper!!.smoothScrollToPosition(position!!)
                }
            }
        })
    }

    /*初始化ToolBar*/
    private fun initToolBar() {
        rv_toolbar?.title = resources.getText(R.string.rv_index_title)
        rv_toolbar?.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        rv_toolbar.setNavigationOnClickListener {
            this.finish()
        }
    }

    private var mRvAdapter: RecyclerViewIndexAdapter? = null
    private var bgaDelegate : BGADivider.StickyDelegate? = null
    private var mScrollHelper : BGARVVerticalScrollHelper ? = null
}
