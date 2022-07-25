package com.android.jetpack.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.android.jetpack.workmanager.databinding.ActivityWorkManagerBinding
import com.android.jetpack.workmanager.wm.KEY_NAME
import com.android.jetpack.workmanager.wm.KEY_NAME_RESULT
import com.android.jetpack.workmanager.wm.UploadWorker1
import com.android.jetpack.workmanager.wm.UploadWorker2

class WorkManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    fun initViews() {
        binding.btnTask1.setOnClickListener {
            task1()
        }

        binding.btnTask2.setOnClickListener {
            task2()
        }
    }

    /**
     * 1、立即执行
     */
    fun task1() {
        val uploadWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(UploadWorker1::class.java).build()
        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }

    /**
     * 2、互相传递数据
     */
    fun task2() {
        val data = Data.Builder().putString(KEY_NAME, "felix").build()
        val uploadWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(UploadWorker2::class.java)
            .setInputData(data)
            .build()

        // livedata 监听执行结果
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(uploadWorkRequest.id).observe(this,
            Observer {
                // 这里是状态，会有多个callback
                if (it.state.isFinished) {
                    val result = it.outputData.getBoolean(KEY_NAME_RESULT, false)
                    Log.e("WorkManager", "result = $result")
                }
            })

        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }
}