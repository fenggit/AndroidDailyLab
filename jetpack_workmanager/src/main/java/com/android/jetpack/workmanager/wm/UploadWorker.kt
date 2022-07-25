package com.android.jetpack.workmanager.wm

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

/**
 * author : hefeng
 * date : 2022/7/25
 * description :
 */
class UploadWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    val TAG = "UploadWorker"

    // 后台异步任务
    override fun doWork(): Result {

        Log.e(TAG, "start upload ...")
        val isResult = uploadImages()
        Log.e(TAG, "upload result = $isResult")

        return if (isResult) Result.success() else Result.failure()
    }

    fun uploadImages(): Boolean {
        Thread.sleep(3000)
        return Random().nextInt(10) % 2 == 2
    }
}