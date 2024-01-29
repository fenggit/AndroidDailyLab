package com.test.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.btn_login).setOnClickListener {

        }

        findViewById<Button>(R.id.btn_login_coroutine).setOnClickListener {

        }

        findViewById<Button>(R.id.btn_sleep).setOnClickListener {
            // 挂起：点击多次，不会卡住主线程，不影响
            lifecycleScope.launch(Dispatchers.Main) {
                delay(10_000) // public suspend fun delay(timeMillis: Long)
                Log.d(TAG, "suspend , thread : ${Thread.currentThread().name}")
            }

            // 阻塞：点击多次会卡住主线程，ANR
            //Thread.sleep(10_000)
            //Log.d(TAG, "sleep , thread : ${Thread.currentThread().name}")
        }
    }

    fun start() {
        val coronitue = suspend { // 协程体
            // TODO
            10
        }.createCoroutine(object : Continuation<Int> { // 创建协程
            override val context: CoroutineContext get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {
                Log.d("test", "coroutine result = ${result.getOrNull()}")
            }
        })

        // 启动协程
        coronitue.resume(Unit)
    }

    fun start2() {
        // 启动协程
        lifecycleScope.launch(Dispatchers.Main) {
            // TODO
            Log.d("test", "coroutine start")
        }
    }

    fun start3(){
        runBlocking {

        }
    }
}