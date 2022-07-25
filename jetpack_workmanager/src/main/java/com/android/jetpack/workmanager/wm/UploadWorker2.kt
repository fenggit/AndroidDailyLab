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
 * description : 传递参数
 */

val KEY_NAME = "com.android.jetpack.workmanager.wm.KEY_NAME"
val KEY_NAME_RESULT = "com.android.jetpack.workmanager.wm.KEY_NAME_RESULT"

class UploadWorker2(appContext: Context, var workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    val TAG = "UploadWorker"

    override fun doWork(): Result {
        // 接收的数据
        val name = workerParams.inputData.getString(KEY_NAME)
        Log.e(TAG, "$name start upload ...")
        val isResult = uploadImages()
        val data = Data.Builder().putBoolean(KEY_NAME_RESULT, isResult).build()
        return if (isResult) Result.success(data) else Result.failure(data)
    }

    fun uploadImages(): Boolean {
        Thread.sleep(3000)
        return Random().nextInt(100) % 2 == 1
    }
}