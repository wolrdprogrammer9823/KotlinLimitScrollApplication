package com.heng.kotlinlimitscrollapplication.adpater
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.interfaces.IOnItemClickListener
import com.heng.kotlinlimitscrollapplication.util.doLog
import kotlinx.android.synthetic.main.tab1_rv_item.view.*

class Tab1Adapter(private var context: Context?,
                  onItemClickListener: IOnItemClickListener<String>)
                 : BaseRecyclerViewAdapter<String,Tab1Adapter.Tab1ViewHolder>(onItemClickListener) {

    private var targetPosition = -1
    private var listData: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tab1ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tab1_rv_item, parent, false)
        return Tab1ViewHolder(view)
    }

    override fun getItemCount(): Int = listData?.size!!

    override fun onBindViewHolder(holder: Tab1ViewHolder, position: Int) {

        holder.itemView.tag = position
        holder.itemView.setOnClickListener(OnTabClickListener(listData!![position]))

        //setItemBackground(targetPosition == position, holder.itemView)

        holder.itemView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //setItemBackground(targetPosition == position, holder.itemView)
                    holder.itemView.setBackgroundColor(context!!.resources.getColor(android.R.color.holo_purple))
                    holder.itemView.postDelayed({
                        holder.itemView.setBackgroundColor(context!!.resources.getColor(android.R.color.holo_orange_light))
                    },200)
                    doLog("MotionEvent.ACTION_DOWN")
                }
                MotionEvent.ACTION_UP -> {
                    //holder.itemView.setBackgroundColor(context!!.resources.getColor(android.R.color.holo_red_dark))
                    holder.itemView.setBackgroundColor(context!!.resources.getColor(android.R.color.holo_orange_light))
                    targetPosition = position
                    doLog("MotionEvent.ACTION_UP")
                }
//                MotionEvent.ACTION_CANCEL -> {
//                    //setItemBackground(targetPosition == position, holder.itemView)
//                    holder.itemView.setBackgroundColor(context!!.resources.getColor(android.R.color.holo_orange_light))
//                    doLog("MotionEvent.ACTION_CANCEL")
//                }
            }
            return@setOnTouchListener false
        }

        holder.itemView.center_content_tv.text = listData!![position]
        holder.itemView.left_icon_iv.background = context?.getDrawable(R.drawable.item)
        holder.itemView.right_icon_iv.background = context?.getDrawable(R.drawable.add_more)

        holder.itemView.right_icon_iv.setOnClickListener { View.OnClickListener {
            Toast.makeText(context!!,"haahs",Toast.LENGTH_SHORT).show()
            doLog("holder.itemView.right_icon_iv")
        } }

//        holder.itemView.setOnClickListener {
//            tab1ClickListener?.let {
//                val content = listData!![position]
//                val itemPos = position
//            }
//        }

    }

    class Tab1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    /*定义一个函数式接口*/
//    private var tab1ClickListener: ((content: String, position: Int) -> Unit?)? = null
//
//    fun setTabClickListener(tab1ClickListener: (content: String, position: Int) -> Unit) {
//        this.tab1ClickListener = tab1ClickListener
//    }

    /*设置item的背景色*/
    private fun setItemBackground(targetPosition: Boolean, itemView: View) {
        val colorValue = if (targetPosition) {
            android.R.color.holo_red_dark
        } else {
            android.R.color.holo_purple
        }
        itemView.setBackgroundColor(context!!.resources.getColor(colorValue))
    }

    /*
    * 设置数据集
    * */
    fun setDataSet(dataSet: List<String>): Unit {
        this.listData = dataSet
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        this.listData = this.listData!!.filter {
            it != this.listData!![position]
        }
        notifyDataSetChanged()
    }
}