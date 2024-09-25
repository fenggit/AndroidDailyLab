package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlin.concurrent.thread

class LiveDataActivity : AppCompatActivity() {
    var liveData = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        liveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.e("LiveDataActivity", "liveData : $t")
            }
        })

        findViewById<Button>(R.id.btn_livedata).setOnClickListener {
            liveData.setValue("hello")
        }

        findViewById<Button>(R.id.btn_livedata_thread).setOnClickListener {
            thread {
                liveData.postValue("hello( child thread )")
            }
        }



        // 监听
        SingleLiveData.info1.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                // update ui
                Log.e("LiveDataActivity", "SingleLiveData : $t")
            }
        })


        // 数据更新
        SingleLiveData.info1.value = "default value" // 主线程
        thread {
            Thread.sleep(3000)
            SingleLiveData.info1.postValue("hello( child thread )") // 子线程
        }


        // 无法感知生命周期，不持有 Lifecycle
        SingleLiveData.info1.observeForever(object : Observer<String> {
            override fun onChanged(t: String?) {
                // update ui
                Log.e("LiveDataActivity", "SingleLiveData : $t")
            }
        })

    }
}