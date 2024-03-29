package com.android.daily.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.daily.databinding.FragmentHomeBinding
import com.android.daily.view.MainViewActivity
import com.androiddaily.mvp.MVPMainActivity
import com.example.kt.KtMainActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.moduleJetpack.setOnClickListener {
            startActivity(Intent(activity, JetpackActivity::class.java))
        }

        binding.moduleView.setOnClickListener {
            startActivity(Intent(activity, MainViewActivity::class.java))
        }

        binding.moduleArchitecture.setOnClickListener {
            startActivity(Intent(activity, MVPMainActivity::class.java))
        }

        binding.moduleKotlin.setOnClickListener {
            startActivity(Intent(activity, KtMainActivity::class.java))
        }

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}