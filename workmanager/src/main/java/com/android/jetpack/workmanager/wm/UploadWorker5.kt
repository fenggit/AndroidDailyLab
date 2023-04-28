package com.android.jetpack.workmanager.wm

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

/**
 * author : hefeng
 * date : 2022/7/25
 * description : 及时任务
 */
class UploadWorker5(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    val TAG = "UploadWorker"

    /**
     * 后台异步任务
     */
    override fun doWork(): Result {
        Log.e(TAG, "start upload ...  $this")
        uploadImages()

        // 重试，结合setBackoffCriteria使用
        return Result.retry()
    }

    fun uploadImages() {
        Thread.sleep(1000)
    }
}