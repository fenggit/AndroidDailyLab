package com.example.kt.coroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author : felix
 * date : 2023/7/5
 * description :
 */
class MyViewModel : ViewModel() {
    val data = MutableLiveData<String>()

    fun startCoroutines() {
        viewModelScope.launch(Dispatchers.Main) {

        }
    }

    fun start() {
        // 会卡当前线程
        runBlocking {
            delay(1000)
        }
    }

    fun start3() {
        runBlocking {
            val d = async {
                delay(1000)
            }

            // 这样，不会卡当前线程
            d.await()
        }
    }

    fun start2() {
        runBlocking {
            // 不会卡当前线程，launch启动后台调度线程，并且不堵塞当前线程
            val a = GlobalScope.async {
                delay(1000)
                return@async "123"
            }

            val res = a.await()
        }
    }
}