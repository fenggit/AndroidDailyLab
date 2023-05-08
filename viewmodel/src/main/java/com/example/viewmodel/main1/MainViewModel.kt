package com.example.viewmodel.main1

import androidx.lifecycle.ViewModel

/**
 * author : felix
 * date : 2023/5/6
 * description :
 */
class MainViewModel : ViewModel() {
    private val usd_to_eu_rate = 0.74f
    private var dollarText = ""
    private var result: Float = 0f

    fun setAmount(value: String) {
        this.dollarText = value
        result = value.toFloat() * usd_to_eu_rate
    }

    fun getResult(): Float {
        return result
    }
}