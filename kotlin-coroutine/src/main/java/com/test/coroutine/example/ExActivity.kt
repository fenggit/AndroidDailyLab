package com.test.coroutine.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.coroutine.R
import com.test.coroutine.databinding.ActivityExBinding

class ExActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}