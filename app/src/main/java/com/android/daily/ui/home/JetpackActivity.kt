package com.android.daily.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.daily.databinding.ActivityJetpackBinding
import com.android.daily.ui.home.jetpack.JetpackAdapter

class JetpackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJetpackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetpackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.jetpackListView.adapter = JetpackAdapter()
        binding.jetpackListView.layoutManager = LinearLayoutManager(this)
    }
}