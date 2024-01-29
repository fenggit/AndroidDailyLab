package com.android.jetpack.workmanager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.*
import com.android.jetpack.workmanager.databinding.ActivityWorkManagerBinding
import com.android.jetpack.workmanager.wm.*
import java.util.concurrent.TimeUnit

/**
 * 场景：每个10分钟，向文件写入日志
 */
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

        binding.btnTask3.setOnClickListener {
            task3()
        }

        binding.btnTask4.setOnClickListener {
            task4()
        }

        binding.btnTask5.setOnClickListener {
            task5()
        }

        binding.btnTask6.setOnClickListener {
            task6()
        }

        binding.btnTask7.setOnClickListener {
            task7()
        }
    }

    /**
     * 1、立即执行
     */
    fun task1() {
        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadWorker1::class.java).build()
        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }

    /**
     * 2、互相传递数据
     */
    fun task2() {
        val data = Data.Builder().putString(KEY_NAME, "felix").build()
        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadWorker2::class.java)
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

    /**
     * 3、执行多任务
     */
    fun task3() {
        val task1 = OneTimeWorkRequest.Builder(UploadWorker3A::class.java).build()
        val task2 = OneTimeWorkRequest.Builder(UploadWorker3B::class.java).build()
        val task3 = OneTimeWorkRequest.Builder(UploadWorker3C::class.java).build()
        val task4 = OneTimeWorkRequest.Builder(UploadWorker3D::class.java).build()

        val taskList = mutableListOf<OneTimeWorkRequest>()
        taskList.add(task1)
        taskList.add(task3)

        // 先执行1，3，再执行2，4
        WorkManager.getInstance(applicationContext)
            .beginWith(taskList) // task1 和 task3 同时执行
            .then(task2)  // 上面执行完，再执行 task2
            .then(task4)  // 上面执行完，再执行 task4，检查上面所有任务
            .enqueue()
    }

    /**
     * 4、
     * 断开网路
     * 杀死app
     * 重新连接网路
     * 可以看到异步任务执行了
     *
     * Google说明：任务一定执行，重启或杀掉app，只要达到条件就会执行（内部是Room保存数据）
     */
    fun task4() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // 连接网路
            .build()

        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadWorker1::class.java)
            .setConstraints(constraints) // 添加执行条件
            .build()
        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }

    /**
     * 5、约束条件（手机充电，内存很多，网络连接）再同步数据
     *
     * 断开网路，充电中，空闲
     * 杀死app
     * 重新连接网路
     * 可以看到异步任务执行了
     */
    fun task5() {
        val build = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // 连接网路
            .setRequiresCharging(true)   // 充电中

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            build.setRequiresDeviceIdle(true)  // 空闲中，比如你玩游戏的时候，不执行(最低API 23开始支持)
        }

        val constraints = build.build()

        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadWorker1::class.java)
            .setConstraints(constraints) // 添加执行条件
            .build()
        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }

    /**
     * 6、重复执行任务
     */
    fun task6() {
        //  循环轮询：最少15分钟重复1次，也就意味着设置低于15分钟是无效的，还是15分钟重复1次
        val task = PeriodicWorkRequest.Builder(UploadWorker4::class.java, 10, TimeUnit.SECONDS).build()
        WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(task.id).observe(this, Observer {
            Log.e("WorkManager", "task6 = ${it.state.name} || isFinish = ${it.state.isFinished}")
        })
        WorkManager.getInstance(applicationContext).enqueue(task)
    }

    /**
     * 7、重复执行任务
     */
    fun task7() {
        //val data = Data.Builder().putString(KEY_NAME, "felix").build()
        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadWorker5::class.java)
            // LINEAR：下次重试时间以线性的方式延迟，EXPONENTIAL：重试时间以指数的方式延迟，最低是10s重试一次
            .setBackoffCriteria(BackoffPolicy.LINEAR, 2, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }
}