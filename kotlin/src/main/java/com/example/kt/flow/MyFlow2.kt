package com.example.kt.flow

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * author : felix
 * date : 2023/7/18
 * description :
 */
class MyFlow2 {
    val TAG = "MyFlow"

    /**
     * 热流：有缓冲区
     */
    fun start() {
        val flow = MutableSharedFlow<Int>()
        runBlocking {
            // emit 马上就发送，不管有没有订阅者
            flow.emit(1)
            flow.emit(2)

            // 此时订阅，没有数据
            flow.collect {
                Log.i(TAG, "val = $it")
            }
        }
    }


    fun start2() {
        val flow = MutableSharedFlow<Int>()
        runBlocking {
            // 这里是异步的
            withContext(Dispatchers.IO) {
                // 订阅，等待数据
                flow.collect {
                    Log.i(TAG, "val = $it")
                    delay(1000)
                }
            }

            // 这里也是异步的
            // 这里有个问题：就是发送的会放到缓冲区，collect 那边 sleep 1s，这边也会等待
            flow.emit(1)
            flow.emit(2)
        }
    }

    fun start3() {
        // 只有 1 个缓冲区
        val flow = MutableSharedFlow<Int>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        runBlocking {
            // 这里是异步的
            withContext(Dispatchers.IO) {
                // 订阅，等待数据
                flow.collect {
                    Log.i(TAG, "val = $it")
                    delay(1000)
                }
            }

            // 这时候不会等待，直接执行了
            flow.emit(1)
            flow.emit(2)
        }
    }
}