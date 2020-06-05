package com.heng.kotlinlimitscrollapplication.adpater
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.interfaces.IOnItemClickListener
import kotlinx.android.synthetic.main.tab2_rv_item.view.*
import kotlin.random.Random
import kotlin.random.asJavaRandom

class Tab2Adapter( private var context : Context?,
                   onItemClickListener:IOnItemClickListener<String>)
                 : BaseRecyclerViewAdapter<String,Tab2Adapter.Tab2Holder>(onItemClickListener) {

    public var listData : List<String>? = null
    private val random = Random.asJavaRandom()

    private val colors = arrayOf(android.R.color.holo_blue_light,
                                 android.R.color.holo_green_light,
                                 android.R.color.holo_blue_dark,
                                 android.R.color.holo_red_dark,
                                 android.R.color.holo_red_light)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tab2Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.tab2_rv_item, parent, false)
        return Tab2Holder(view)
    }

    override fun onBindViewHolder(holder: Tab2Holder, position: Int) {
        val colorDrawable = ColorDrawable(context!!.resources.getColor(colors[random.nextInt(colors.size)]))
        holder.itemView.tab2_rv_item_tv.background = colorDrawable
        holder.itemView.setOnClickListener(OnTabClickListener(listData!![position]))
        holder.itemView.tab2_rv_item_tv.text = listData!![position]
        holder.itemView.tag = position.plus(1)
    }

    override fun getItemCount(): Int = listData?.size!!

    class Tab2Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

   fun setDataSet(dataSet: List<String>) {
        this.listData = dataSet
        notifyDataSetChanged()
    }
}