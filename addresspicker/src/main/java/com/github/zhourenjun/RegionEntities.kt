
package com.github.zhourenjun

internal data class Province(val name: String, val cities: ArrayList<City>?)

internal data class City(val name: String, val districts: ArrayList<District>?)

internal data class District(val name: String, val zipCode: String)