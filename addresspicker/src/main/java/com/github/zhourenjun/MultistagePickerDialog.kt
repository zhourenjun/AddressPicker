package com.github.zhourenjun

import android.content.Context
import com.epro.scp.view.widget.addressPicker.MultistagePickerDataProvider
import com.google.android.material.bottomsheet.BottomSheetDialog


// Note: 此时利用了 Kotlin 的类委托特性 来实现 装饰器模式，或者说代理模式
open class MultistagePickerDialog private constructor(context: Context, private val picker: MultistagePicker) : PickerDialogInterface, PickerInterface by picker {

    constructor(context: Context, dataProvider: MultistagePickerDataProvider) : this(context, MultistagePicker(context, dataProvider))

    private val dialog = BottomSheetDialog(context)

    init {
        picker.showConfirmButton(true)
        dialog.setContentView(picker.getView())
        dialog.apply {
            // 屏蔽BottomDialog的下滑隐藏
//            setCancelable(false)
            setCanceledOnTouchOutside(true)
            val p = window?.attributes
            p?.height = (context.resources.displayMetrics.heightPixels / 3.0F).toInt()
            window?.attributes = p
        }
    }

    override fun setOnPickCompletedListener(l: (selectedOptions: Map<String, String>) -> Unit) {
        picker.setOnPickCompletedListener {
            l.invoke(it)
            hide()
        }
    }

    override fun show() {
        dialog.show()
    }

    override fun hide() {
        picker.getView().postDelayed( { dialog.dismiss() }, 200)
    }
}