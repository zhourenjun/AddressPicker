package com.zrj.addresspicker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.github.zhourenjun.RegionPicker

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.tv)
        tv.setOnClickListener {
            val regionPicker = RegionPicker(this)
            regionPicker.setOnAddressPickSuccessListener { region ->
                tv.text = "${region.province}${region.city}${region.district}"
            }
            regionPicker.show()
        }
    }
}
