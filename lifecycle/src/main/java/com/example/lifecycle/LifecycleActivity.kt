package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.lifecycle.cus.MyObserver
import com.example.lifecycle.cus.MyOwner
import com.example.lifecycle.observer.LifeObserver1
import com.example.lifecycle.observer.LifeObserver2
import com.example.lifecycle.observer.LifeObserver3

class LifecycleActivity : AppCompatActivity() {
    var myOwner = MyOwner()
    var myObserver = MyObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

//        lifecycle.addObserver(LifeObserver1())
//        lifecycle.addObserver(LifeObserver2())
//        lifecycle.addObserver(LifeObserver3())

        lifecycle.addObserver(LifeObserver2())

        myOwner.lifecycle.addObserver(myObserver)
        findViewById<Button>(R.id.btn_life_start).setOnClickListener {
            myOwner.start()
        }

        findViewById<Button>(R.id.btn_life_release).setOnClickListener {
            myOwner.destroy()
        }
    }

    override fun onResume() {
        super.onResume()
//        lifecycle.addObserver(LifeObserver2())
    }
}