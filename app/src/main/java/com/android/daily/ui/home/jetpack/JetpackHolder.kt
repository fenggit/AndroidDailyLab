package com.android.daily.ui.home.jetpack

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.daily.databinding.ItemJetpackBinding

/**
 * author : hefeng
 * date : 2022/7/25
 * description :
 */
class JetpackHolder(var binding: ItemJetpackBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(info: JetpackInfo) {
        binding.tvWorkManagerTitle.text = info.title
        binding.ivWorkManager.load(info.imageUrl)
        binding.viewCard.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context, info.target))
        }
    }
}