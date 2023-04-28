package com.example.lifecycle.cus

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * author : felix
 * date : 2023/4/17
 * description :
 */
class MyObserver : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.i("MyObserver", "MyObserver >>>>> ${event.name}")
    }
}