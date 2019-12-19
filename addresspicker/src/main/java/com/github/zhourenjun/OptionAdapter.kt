package com.github.zhourenjun

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView


internal class OptionAdapter constructor(val data: MutableList<String>) : RadioRcvAdapter<OptionVH>() {

    constructor() : this(mutableListOf<String>())

    private lateinit var mContext: Context
    private lateinit var mLayoutInflater: LayoutInflater

    private var mItemViewClickListener: ((view: View, position: Int, option: String) -> Unit)? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mContext = recyclerView.context
        mLayoutInflater = LayoutInflater.from(mContext)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolderWrapper(parent: ViewGroup, viewType: Int): OptionVH {
        return OptionVH(mLayoutInflater.inflate(R.layout.mp_option_view, parent, false))
    }

    override fun onBindViewHolderWrapper(holder: OptionVH, position: Int, selected: Boolean) {
        holder.textView.text = data[position]
        holder.textView.tag = position
        holder.img.setVisible(selected)
    }

    override fun onSelectedItemChanged(itemView: View, position: Int) {
        mItemViewClickListener?.invoke(itemView, position, data[position])
    }

    fun setOnItemViewClickListener(l: (v: View, position: Int, option: String) -> Unit) {
        mItemViewClickListener = l
    }

}

internal class OptionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.optionText)
    val img: AppCompatImageView = itemView.findViewById(R.id.iv)
}


fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
