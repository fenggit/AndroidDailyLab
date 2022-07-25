package com.android.jetpack.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.android.jetpack.workmanager.databinding.ActivityWorkManagerBinding
import com.android.jetpack.workmanager.wm.UploadWorker

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
            val uploadWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java).build()
            WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
        }
    }
}