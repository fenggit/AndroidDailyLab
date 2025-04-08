package com.example.lifecycle.cus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry


/**
 * author : felix
 * date : 2023/4/17
 * description :
 */
class MyOwner : LifecycleOwner {
    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override val lifecycle: Lifecycle = lifecycleRegistry

    init {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    fun start() {
        //lifecycleRegistry.currentState = Lifecycle.State.CREATED
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun stop() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    fun destroy() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}