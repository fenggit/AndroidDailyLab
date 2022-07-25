package com.android.daily.ui.home.jetpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.daily.databinding.ItemJetpackBinding

/**
 * author : hefeng
 * date : 2022/7/25
 * description :
 */
class JetpackAdapter : RecyclerView.Adapter<JetpackHolder>() {
    val list = JetpackDataUtils.getJetpackListData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JetpackHolder {
        val binding = ItemJetpackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JetpackHolder(binding)
    }

    override fun onBindViewHolder(holder: JetpackHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}