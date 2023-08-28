package com.avangard.bratstvo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avangard.bratstvo.databinding.FragmentPointsDetailBinding
import com.avangard.bratstvo.databinding.PointsDetailAdapterBinding
import com.avangard.bratstvo.profile.root.presentation.History
import com.avangard.bratstvo.profile.root.presentation.UserHistoryUiModel
import com.bumptech.glide.Glide
import kotlinx.coroutines.currentCoroutineContext
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.coroutineContext

class PointsDetailAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<History> = mutableListOf()


    inner class ItemViewH(val binding: PointsDetailAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: History) {
               binding.tvCountBonus.text  = item.prize.price.toString()
            binding.tvDescription.text  = item.prize.name
            binding.tvDate.text = item.prize.date
            Glide.with(binding.root.context).load(item.bought).into(binding.imHistory)





            }
//
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewH(
            PointsDetailAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewH).bind(list[position])

    }


    override fun getItemCount(): Int {
        return list.size
    }
}