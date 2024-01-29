package com.example.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * author : felix
 * date : 2023/9/5
 * description :
 */
class KtMainActivity2 : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch {
            // TODO
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}