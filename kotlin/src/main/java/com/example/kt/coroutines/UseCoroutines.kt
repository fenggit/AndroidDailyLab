package com.example.kt.coroutines

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author : felix
 * date : 2023/7/13
 * description :
 */
class UseCoroutines(val lifecycleScope: LifecycleCoroutineScope) {
    val TAG = "UseCoroutines"

    fun startGlobal() {
        GlobalScope.launch { // Android上默认是在子线程

        }
    }

    fun switchDispatchers() { // 结果 ：1 3 4 2
        lifecycleScope.launch(Dispatchers.Main) { // main
            Log.i(TAG, "1")
            launch(Dispatchers.Main, start = CoroutineStart.DEFAULT) { // 封装成 runnable，需要时间
                Log.i(TAG, "2")
            }

            launch(Dispatchers.Main, start = CoroutineStart.UNDISPATCHED) {
                Log.i(TAG, "3")
            }
            Log.i(TAG, "4")
        }
    }

    fun switchContext() {
        lifecycleScope.launch(Dispatchers.Main) { // main

            launch(Dispatchers.IO) {  // io

            }

            launch(Dispatchers.IO) {  // io

            }
        }
    }

    fun startLazy() {
        val job = lifecycleScope.launch(start = CoroutineStart.LAZY) {

        }
        // 执行协程
        job.start()
    }

    fun mulTask() {
        lifecycleScope.launch {// 同时执行多个任务
            val user1 = async(Dispatchers.IO) {
                fetchUserInfo("Jack")
            }

            val user2 = async(Dispatchers.IO) {
                fetchUserInfo("Tom")
            }

            val userInfo1 = user1.await()
            val userInfo2 = user2.await()

            Log.i(TAG, "userInfo1=$userInfo1")
            Log.i(TAG, "userInfo2=$userInfo2")
        }
    }

    suspend fun fetchUserInfo(uerUuid: String): String {
        withContext(Dispatchers.IO) {
            delay(2000L) // 模拟 网络请求
        }
        return "{\"userUuid\":$uerUuid,\"userName\":\"zhangsan\"}"
    }
}