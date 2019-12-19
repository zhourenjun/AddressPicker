package com.github.zhourenjun

import android.content.Context


class RegionPicker private constructor(private val picker: MultistagePickerDialog) : PickerDialogInterface by picker, PickerConfigInterface by picker {

    constructor(context: Context) : this(MultistagePickerDialog(context, AddressPickerDataProvider(context)))

    private var addressPickSuccessListener: ((region: Region) -> Unit)? = null

    init {
        setTitle("所在地区")
        picker.setOnPickCompletedListener { selectedOptions ->
            val region = Region (
                    selectedOptions[STAGE_KEY_PROVINCE] ?: "",
                    selectedOptions[STAGE_KEY_CITY] ?: "",
                    selectedOptions[STAGE_KEY_DISTRICT] ?: ""
            )
            addressPickSuccessListener?.invoke(region)
        }
    }

    fun setDefaultRegion(region: Region?) {
        region?.let {
            val options = mutableMapOf(
                    STAGE_KEY_PROVINCE to it.province,
                    STAGE_KEY_CITY to it.city,
                    STAGE_KEY_DISTRICT to it.district
            )
            picker.setPreselectedOptions(options)
        }
    }

    fun setOnAddressPickSuccessListener(l: (region: Region) -> Unit) {
        addressPickSuccessListener = l
    }

    data class Region(val province: String, val city: String, val district: String)
}
