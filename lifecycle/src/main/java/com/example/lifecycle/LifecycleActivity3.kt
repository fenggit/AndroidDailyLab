package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.lifecycle.cus.MyObserver
import com.example.lifecycle.cus.MyOwner
import com.example.lifecycle.observer.LifeObserver1
import com.example.lifecycle.observer.LifeObserver2
import com.example.lifecycle.observer.LifeObserver3

/**
 * 在不同的地方注册观察者，初始化的时候，触发的生命周期不一样
 */
class LifecycleActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        // onCreate -> onStart -> onResume
        //lifecycle.addObserver(LifeObserver3())
    }

    override fun onResume() {
        super.onResume()
        // onCreate -> onStart -> onResume
        // lifecycle.addObserver(LifeObserver3())
    }

    override fun onStop() {
        super.onStop()
        // onPause -> onStop -> onCreate
        lifecycle.addObserver(LifeObserver3())
    }
}