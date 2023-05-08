package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.main2.MainViewModel

class ViewModelActivity : AppCompatActivity() {
    lateinit var myViewModel: MyViewModel

    val myViewModel2 by viewModels<MyViewModel>()

    private lateinit var viewModel: MainViewModel
    val factory = SavedStateViewModelFactory(application, this)

    lateinit var dollarText: EditText
    lateinit var resultText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        // 1. ViewModel 是怎么创建的？
        // 2. ViewModel 是怎么存储的？
        // 3. ViewModel 为什么旋转屏幕，数据没有丢失？

        myViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MyViewModel::class.java)
        myViewModel.data = "1212"

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        dollarText = findViewById(R.id.dollarText)
        resultText = findViewById(R.id.resultText)

        resultText.text = viewModel.getResult().toString()

        findViewById<Button>(R.id.convertButton).setOnClickListener {
            if (dollarText.text.isNotEmpty()) {
                viewModel.setAmount(dollarText.text.toString())
                resultText.text = viewModel.getResult().toString()
            } else {
                resultText.text = "No Value"
            }
        }
    }
}