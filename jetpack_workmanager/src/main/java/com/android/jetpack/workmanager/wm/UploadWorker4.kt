package com.android.jetpack.workmanager.wm

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

/**
 * author : hefeng
 * date : 2022/7/25
 * description :
 */
class UploadWorker4(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    val TAG = "UploadWorker"

    override fun doWork(): Result {
        Log.e(TAG, "Task4 upload image ...")
        uploadImages()
        Log.e(TAG, "Task4 upload image success...")
        return Result.success()
    }

    fun uploadImages(): Boolean {
        Thread.sleep(3000)
        return Random().nextInt(100) % 2 == 1
    }
}