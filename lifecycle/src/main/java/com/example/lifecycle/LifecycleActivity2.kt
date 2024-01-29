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

class LifecycleActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycle.addObserver(LifeObserver())
    }

    inner class LifeObserver : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
        }
    }
}