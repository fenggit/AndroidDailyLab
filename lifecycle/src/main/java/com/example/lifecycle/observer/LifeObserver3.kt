package com.example.lifecycle.observer

import android.util.Log
import androidx.lifecycle.*

/**
 * author : felix
 * date : 2023/4/17
 * description : 推荐
 */
class LifeObserver3 : DefaultLifecycleObserver {
    val TAG = "LifeObserver3"

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.i(TAG, ">>>>> onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i(TAG, ">>>>> onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i(TAG, ">>>>> onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i(TAG, ">>>>> onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i(TAG, ">>>>> onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i(TAG, ">>>>> onDestroy")
    }
}