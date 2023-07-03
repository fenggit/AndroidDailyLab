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
            liveData.setValue( "hello")
        }

        findViewById<Button>(R.id.btn_livedata_thread).setOnClickListener {
            thread {
                liveData.postValue("hello( child thread )")
            }
        }
    }
}