package com.example.lifecycle.observer

import android.util.Log
import androidx.lifecycle.*

/**
 * author : felix
 * date : 2023/4/17
 * description : 推荐
 */
class LifeObserver2 : LifecycleEventObserver {
    val TAG = "LifeObserver2"

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.i(TAG, ">>>>> ${event.name}")
    }
}