package com.github.zhourenjun

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


internal inline fun <reified T> Any?.safeCast(action: T.() -> Unit) {
    if (this is T) {
        this.action()
    }
}

// 将指定的 item 滚动至 RecyclerView 视图的顶部
// Note: 目前只支持 LinearLayoutManager
internal fun RecyclerView.scrollToPositionAtTop(position: Int) {
    val itemCount = this.adapter?.itemCount ?: 0
    if (position < 0 || position >= itemCount) {
        return
    }
    if (layoutManager is LinearLayoutManager) {
        val lm = layoutManager as LinearLayoutManager
        val firstPosition = lm.findFirstVisibleItemPosition()
        val lastPosition = lm.findLastVisibleItemPosition()
        when {
            // 指定的 item 位于 RecyclerView 的上方（不可见）
            position <= firstPosition -> this.scrollToPosition(position)
            // 指定的 item 位于 RecyclerView 视图中 （可见）
            position <= lastPosition -> {
                val targetView = lm.findViewByPosition(position)
                this@scrollToPositionAtTop.scrollBy(0, targetView?.top ?: 0)
            }
            // 指定的 item 位于 RecyclerView 视图下方 （不可见）
            position > lastPosition -> {
                // 1、将指定的 item 滚动至 RecyclerView 视图内
                // 2、监听 RecyclerView 的滚动事件，在指定 item 滚动至 RecyclerView 视图后，
                //    再通过 scrollBy 的方式将 item 滚动至 视图顶部
                this.scrollToPosition(position)
                var enabled = true

                val l = object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (enabled) {
                            enabled = false
                            val targetView = lm.findViewByPosition(position)
                            this@scrollToPositionAtTop.scrollBy(0, targetView?.top ?: 0)
                        }
                    }
                }
                this.addOnScrollListener(l)
            }
        }
    }
}