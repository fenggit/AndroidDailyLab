package com.example.kt.channel

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * author : felix
 * date : 2023/7/18
 * description :
 */
class MyChannel {
    val TAG = "MyChannel"

    fun start() {
        val channel = Channel<Int>()
        runBlocking {
            // 生产者
            val product = GlobalScope.launch {
                var i = 0
                while (true) {
                    delay(1000)
                    channel.send(++i)
                    Log.e(TAG, "send : $i")
                }
            }

            // 消费者
            val consumer = GlobalScope.launch {
                while (true) {
                    val data = channel.receive()
                    Log.e(TAG, "receive : $data")
                }
            }

            joinAll(product, consumer)
        }
    }

    /**
     * send发送一次，等待receive消费，才会继续发送
     */
    fun start2() {
        val channel = Channel<Int>()
        runBlocking {
            // 生产者
            val product = GlobalScope.launch {
                var i = 0
                while (true) {
                    delay(1000)
                    channel.send(++i)
                    Log.e(TAG, "send : $i")
                }
            }

            // 消费者
            val consumer = GlobalScope.launch {
                while (true) {
                    delay(2000)
                    val data = channel.receive()
                    Log.e(TAG, "receive : $data")
                }
            }

            joinAll(product, consumer)
        }
    }

    fun start3() {
        // Channel.UNLIMITED : 值为Int.MAX_VALUE，可以理解为无限大
        val channel = Channel<Int>(Channel.UNLIMITED)
        runBlocking {
            // 生产者
            val product = GlobalScope.launch {
                for (i in 0..5) {
                    channel.send(i)
                    Log.e(TAG, "send : $i")
                }
            }

            // 消费者
            val consumer = GlobalScope.launch {
                val iterator = channel.iterator()
                while (iterator.hasNext()) {
                    val data = iterator.next()
                    Log.e(TAG, "receive : $data")
                    delay(2000)
                }
            }

            joinAll(product, consumer)
        }
    }
}