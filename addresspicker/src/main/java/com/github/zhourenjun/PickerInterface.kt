package com.github.zhourenjun


interface PickerInterface : PickerConfigInterface {

    fun setOnPickCompletedListener(l: (selectedOptions: Map<String, String>) -> Unit)

    fun setPreselectedOptions(selectedOptions: Map<String, String>)
}