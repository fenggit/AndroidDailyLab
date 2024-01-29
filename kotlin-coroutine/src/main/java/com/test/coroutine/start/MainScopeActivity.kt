package com.test.coroutine.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.coroutine.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScopeActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_scope)

        launch {
            delay(3000) // 模拟耗时操作
            Log.d("test", "coroutine result = ${Thread.currentThread().name}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}