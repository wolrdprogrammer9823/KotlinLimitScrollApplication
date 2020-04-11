package com.heng.kotlinlimitscrollapplication.adpater
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heng.kotlinlimitscrollapplication.R
import com.heng.kotlinlimitscrollapplication.interfaces.IOnItemClickListener
import kotlinx.android.synthetic.main.tab3_rv_item.view.*
import kotlin.random.Random
import kotlin.random.asJavaRandom

class Tab3Adapter(private var context: Context?,
                  onItemClickListener:IOnItemClickListener<String>)
                  :BaseRecyclerViewAdapter<String,Tab3Adapter.Tab3Holder>(onItemClickListener) {

    private var listData: List<String>? = null
    private val random = Random.asJavaRandom()
    private val colors = arrayOf(android.R.color.holo_blue_light,
                                 android.R.color.holo_green_dark,
                                 android.R.color.holo_purple,
                                 android.R.color.holo_red_dark,
                                 android.R.color.holo_red_light)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tab3Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.tab3_rv_item, parent, false)
        return Tab3Holder(view)
    }

    override fun onBindViewHolder(holder: Tab3Holder, position: Int) {
        val colorDrawable = ColorDrawable(context!!.resources.getColor(colors[random.nextInt(colors.size)]))
        val rvLayoutParams : RecyclerView.LayoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
        rvLayoutParams.height = getHeight(100,context!!).plus(getHeight(random.nextInt(50), context!!))
        holder.itemView.tab3_square_layout.background = colorDrawable
        holder.itemView.setOnClickListener(OnTabClickListener(listData!![position]))
        holder.itemView.tab3_rv_item_tv.text = listData!![position]
        holder.itemView.tag = position.plus(1)
    }

    override fun getItemCount(): Int = listData?.size!!

    class Tab3Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    private fun getHeight(baseWidth: Int = 20, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, baseWidth.toFloat(),
            context.resources.displayMetrics).toInt()
    }

    fun setDataSet(dataSet: List<String>) {
        this.listData = dataSet
    }

    fun deleteItem(position: Int) {
        this.listData!!.dropWhile {
            it == listData!![position.minus(1)]
        }
        notifyItemRangeRemoved(position.minus(1), 1)
    }
}