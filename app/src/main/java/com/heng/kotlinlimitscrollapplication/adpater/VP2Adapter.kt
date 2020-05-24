package com.heng.kotlinlimitscrollapplication.adpater
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heng.kotlinlimitscrollapplication.R
import kotlinx.android.synthetic.main.vp2_adpater_item.view.*

class VP2Adapter(context: Context, dataSet: ArrayList<String>)
                : RecyclerView.Adapter<VP2Adapter.VP2Holder>() {

    private var mContext = context
    private var mDataSet = dataSet
    private val bgArray = intArrayOf(
        R.drawable.vp2_item_bg1,
        R.drawable.vp2_item_bg2,
        R.drawable.vp2_item_bg3,
        R.drawable.vp2_item_bg4
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP2Holder {

        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.vp2_adpater_item, parent, false)
        return VP2Holder(view)
    }

    override fun onBindViewHolder(holder: VP2Holder, position: Int) {
       holder.itemView.vp2_item_root_layout.setBackgroundResource(bgArray[position])
       holder.itemView.vp2_item_tv.text = mDataSet[position]
    }

    override fun getItemCount(): Int = mDataSet.size

    class VP2Holder(view: View) : RecyclerView.ViewHolder(view) {}
}