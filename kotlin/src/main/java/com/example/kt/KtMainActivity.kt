package com.example.kt

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

class KtMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt_main)

        findViewById<Button>(R.id.btn_coroutine).setOnClickListener {
            startCoroutinesForCrash()
        }

    }

    /**
     * 阻塞
     */
    fun a() {
        findViewById<Button>(R.id.btn_coroutine2).setOnClickListener {
            // 主线程：阻塞，点击后会卡住
            Thread.sleep(10_000)
            Log.d("test", "delay 10s")
        }
    }

    /**
     * 挂起
     */
    fun b() {
        findViewById<Button>(R.id.btn_coroutine2).setOnClickListener {
            // 主线程：挂起
            lifecycleScope.launch(Dispatchers.Main) {
                delay(10_000)
                Log.d("test", "delay 10s")
            }
        }
    }

    fun c() {
        // 基础设施层  / 业务框架层：lifecycleScope
        // 协程体
        val coronitue = suspend {
            5
        }.createCoroutine(object : Continuation<Int> {
            override val context: CoroutineContext
                get() = EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {
                Log.d("test", "coroutine result = ${result.getOrNull()}")
            }
        })
        // 启动协程
        coronitue.resume(Unit)

        lifecycleScope.launch {

        }

        // lifecycleScope：协程作用域
        // Dispatchers.Main：协程调度器
        // CoroutineStart.DEFAULT: 协程启动模式


        lifecycleScope.launch(Dispatchers.Main, start = CoroutineStart.DEFAULT) {

        }
    }

    fun d(){
        val mainScope = MainScope()
        mainScope.launch(Dispatchers.IO) {

        }
        // 取消
        mainScope.cancel()
    }

    fun startScope() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1000)
        }
    }

    fun startMode() {
        MainScope()
        lifecycleScope.launch(Dispatchers.Main, start = CoroutineStart.DEFAULT) { // Activity 中，推荐这种写法

            launch(Dispatchers.IO) {

            }
        }
    }

    fun startCoroutinesForCrash() {
        val task = GlobalScope.launch {
            val a: String? = null
            Log.e("KtMainActivity", "startCoroutines Main")
            //Log.e("KtMainActivity", "startCoroutines Main: ${a!!.length}")

//            val job = Job()
            val task2 = GlobalScope.launch(Dispatchers.IO) {
                Log.e("KtMainActivity", "startCoroutines start")
                delay(3000)
                Log.e("KtMainActivity", "startCoroutines end")
                // Log.e("KtMainActivity", "startCoroutines IO :  ${a!!.length}")
            }

            delay(500)
            task2.cancelAndJoin()
            //job.cancel()
        }
        task.cancel()
    }

    fun sc() {
        GlobalScope.launch { // Android上默认是在子线程

        }

        GlobalScope.launch(Dispatchers.Main) { // 主线程执行

        }

        // GlobalScope：这个提示是谨慎使用，不是弃用，全局协程，生命周期和应用一样长，使用不好，会导致内存泄漏
        // This is a delicate API and its use requires care. Make sure you fully read and understand documentat
        GlobalScope.launch(Dispatchers.Main) { // 主线程
            val userUuid = fetchUserUuid() // 子线程
            val userInfo = fetchUserInfo(userUuid) // 子线程
            // 更新 UI

        }


        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {  // 切换线程

            }
        }


        // 写法一：不推荐这种
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                // 业务代码

            } catch (e: Exception) { // 协程抛出的异常无法捕获

            }
        }

        // 写法二：推荐这种
        val executionHandler = CoroutineExceptionHandler { _, throwable ->
            val message = throwable.message // error message
        }

        lifecycleScope.launch(Dispatchers.IO + executionHandler) {
            // 业务代码
        }


        val job = lifecycleScope.launch(Dispatchers.Main) {

        }
        job.cancel()
    }


    suspend fun fetchUserUuid(): String {
        withContext(Dispatchers.IO) {
            delay(3000L) // 模拟 网络请求
        }
        return "user34234343434"
    }

    suspend fun fetchUserInfo(uerUuid: String): String {
        withContext(Dispatchers.IO) {
            delay(2000L) // 模拟 网络请求
        }
        return "{\"userUuid\":$uerUuid,\"userName\":\"zhangsan\"}"
    }
}