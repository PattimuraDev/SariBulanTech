package com.example.saribulantech

import com.github.mikephil.charting.formatter.ValueFormatter

class AxisFormatter(private val values: Array<String>) :  ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return if (value >= 0) {
            if (values.size > value.toInt()) {
                values[value.toInt()]
            } else ""
        } else {
            ""
        }
    }
}