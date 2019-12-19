package com.epro.scp.view.widget.addressPicker

abstract class MultistagePickerDataProvider {
    abstract fun stageKeys(): List<String>

    open fun stageTabText(stageKey: String): String {
        return "请选择"
    }

    abstract fun stageData(stageKey: String, upperStageSelectedOptions: Map<String, String>): List<String>

    abstract  fun noDistrict(province: String, city: String): Boolean
}