package com.example.kt.lesson1

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle

/**
 * author : felix
 * date : 2024/10/17
 * description :
 */
class Lesson1 {
    fun regLifecycle(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                }

                override fun onActivityStarted(activity: Activity) {
                }

                override fun onActivityResumed(activity: Activity) {
                }

                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                }

            })
        }
    }

    fun regLifecycle2(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks by noOpDelegate() {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

                }
            })
        }
    }
}