package com.example.viewmodel.main2

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * author : felix
 * date : 2023/5/6
 * description :
 */

const val RESULT_KEY = "com.example.viewmodel.main2.result"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val usd_to_eu_rate = 0.74f
    private var dollarText = ""
    private var result: Float = 0f

    fun setAmount(value: String) {
        this.dollarText = value
        result = value.toFloat() * usd_to_eu_rate

        savedStateHandle.set(RESULT_KEY, result)
    }

    fun getResult(): Float? {
        return savedStateHandle.get(RESULT_KEY)
    }
}