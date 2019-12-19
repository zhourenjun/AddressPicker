package com.github.zhourenjun

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// 支持单选功能的RecyclerView Adapter
abstract class RadioRcvAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val tagKey: Int = R.id.mp_tag_key

    private var selectedItemPosition = -1

    private val internalListener = View.OnClickListener {
        it.getTag(tagKey).safeCast<Int> {
            selectItem(this@safeCast, true)
            onSelectedItemChanged(it, this@safeCast)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vh = onCreateViewHolderWrapper(parent, viewType)
        vh.itemView.setOnClickListener(internalListener)
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setTag(tagKey, position)
        onBindViewHolderWrapper(holder, position, position == selectedItemPosition)
    }

    abstract fun onCreateViewHolderWrapper(parent: ViewGroup, viewType: Int) : VH
    abstract fun onBindViewHolderWrapper(holder: VH, position: Int, selected: Boolean)

    open fun onSelectedItemChanged(itemView: View, position: Int) {

    }

    fun selectItem(position: Int, refresh: Boolean) {
        if (position == selectedItemPosition) {
            return
        }

        val previousItemPosition = selectedItemPosition
        selectedItemPosition = position

        if (refresh) {
            notifyItemChanged(selectedItemPosition)
            if (previousItemPosition >= 0) {
                notifyItemChanged(previousItemPosition)
            }
        }
    }
}