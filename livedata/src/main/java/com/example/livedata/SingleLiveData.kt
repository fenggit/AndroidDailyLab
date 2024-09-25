package com.example.livedata

import androidx.lifecycle.MutableLiveData

/**
 * author : felix
 * date : 2024/2/29
 * description :
 */
object SingleLiveData {  // 单例模式
    val info1 by lazy {  // 懒加载
        MutableLiveData<String>()
    }
}