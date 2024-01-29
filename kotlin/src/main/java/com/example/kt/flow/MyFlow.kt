package com.example.kt.flow

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * author : felix
 * date : 2023/7/17
 * description :
 */
class MyFlow(val lifecycleScope: LifecycleCoroutineScope) {
    val TAG = "MyFlow"

    /**
     * 响应式编程
     * 1、flow 是一个异步数据流，所以要结合协程使用
     * 2、flow 是不能切换线程的，只能简单使用
     * 3、flow 数据流分为上中下游；
     *      - 上游：emit发送数据
     *      - 中游：处理数据
     *      - 下游：订阅，接收数据
     *
     *  4、flow 组合函数，处理数据
     */
    fun startFlow() {
        lifecycleScope.launch {
            flow<Int> {// Flow 上游：发送
                emit(1) // 提交数据
                delay(1000)
                emit(2)
                emit(3)
            }.collect {              // FlowCollector 下游：接收
                Log.e(TAG, "flow-->$it")
            }
        }
    }

    fun startFlow2() {
        runBlocking { // main thread
            val flow = createFlow() // io thread
            flow.collect { // error
                Log.i(TAG, it)
            }
        }
    }

    /**
     * flow 不可以随便切换线程
     */
    fun createFlow(): Flow<String> = flow {
        withContext(Dispatchers.IO) {  // io thread
            emit("io")
        }

        repeat(3) {
            delay(100)
            emit("1111")
            emit("2222")
        }
    }

    /**
     * channelFlow : 可以随意切换线程
     */
    fun createChannelFlow(): Flow<String> = channelFlow {
        withContext(Dispatchers.IO) {  // io thread
            send("io")
        }

        repeat(3) {
            delay(100)
            send("1111")
            send("2222")
        }
    }

    fun asFlow() {
        runBlocking {
            val list = listOf<Int>(1, 2, 3)
            list.asFlow().collect {
                Log.i(TAG, "val = $it")
            }
        }
    }

    fun testFlowOf() {
        runBlocking {
            flowOf(1, 2, 3).collect {
                Log.i(TAG, "val = $it")
            }
        }
    }

    /**
     * 冷数据流：没有 collect 数据收集的时候，是不会触发发送数据
     */
    fun start() {
        lifecycleScope.launch {
            flow<Int> {// Flow 上游：发送
                Log.e(TAG, "flow start") // 并不会执行，因为没有 collect

                emit(1)
                emit(2)
                emit(3)
            }

            Log.e(TAG, "flow end")
        }
    }

    /**
     * flow里面的emit并不是连续执行的，而是执行1次emit后，触发collect
     */
    fun startOrder() {
        lifecycleScope.launch {
            flow<Int> {// Flow 上游：发送
                Log.e(TAG, "flow start")
                emit(1)
                Log.e(TAG, "flow start 1")
                emit(2)
                Log.e(TAG, "flow start 2")
                emit(3)
                Log.e(TAG, "flow start 3")
            }.collect {
                Log.e(TAG, "flow val = $it")
            }
            Log.e(TAG, "flow end")
        }
    }

    /**
     * 中间操作符
     */
    fun start2() {
        // 发送数据，不需要协程域
        val flow = flow<Int> {// Flow 上游：发送
            // 上游：产生数据，发送
            emit(1)
            emit(2)
            emit(3)
            emit(4)
            emit(5)

        // 中游：数据处理
        }.filter { // 只获取大于2的值
            it > 2
        }.map {    // 数据乘以2
            it * 2
        }.catch {// 捕获异常，需要自己重新提交数据
           // TODO
        }.take(2) // 只获取前2条数据

        // 接收数据，需要协程域
        lifecycleScope.launch {
            // 下游：订阅，接收数据
            flow.collect {
                Log.e(TAG, "flow val = $it")
            }
            Log.e(TAG, "flow end")
        }

        // 或者这样
        runBlocking {
            flow.collect {
                Log.e(TAG, "flow val = $it")
            }
            Log.e(TAG, "flow end")
        }
    }
}