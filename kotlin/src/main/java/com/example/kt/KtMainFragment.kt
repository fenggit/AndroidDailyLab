package com.example.kt

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.runBlocking

/**
 * author : felix
 * date : 2023/9/5
 * description :
 */
class KtMainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_kt_main, container)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        runBlocking {  }
    }
}