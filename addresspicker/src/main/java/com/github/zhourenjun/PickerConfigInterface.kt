package com.github.zhourenjun

interface PickerConfigInterface {

    fun setTitle(title: CharSequence?)

    /**
     * @param shown true  - 展示 “确定” 按钮
     *              false - 隐藏 “确定” 按钮
     */
    fun showConfirmButton(shown: Boolean)
}