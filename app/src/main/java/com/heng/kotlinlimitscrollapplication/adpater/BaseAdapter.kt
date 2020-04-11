package com.heng.kotlinlimitscrollapplication.adpater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.heng.kotlinlimitscrollapplication.interfaces.IOnItemClickListener

abstract class BaseRecyclerViewAdapter<Data,Holder : RecyclerView.ViewHolder>
               (private var onItemClickListener: IOnItemClickListener<Data>)
                : RecyclerView.Adapter<Holder>() {

    private val boundTimeValue = 1000
    private var currentTime = 0L
    inner class OnTabClickListener(private val data: Data) : View.OnClickListener {
        override fun onClick(v: View?) {
            if (System.currentTimeMillis() - currentTime > boundTimeValue) {
                val position = v!!.tag as Int
                onItemClickListener!!.onItemClick(v, position, data)
                currentTime = System.currentTimeMillis()
            }
        }
    }

}