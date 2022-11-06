package com.example.login.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.databinding.ItemViewBinding
import com.example.login.taskcontrollernetwork.getmodel.Data

class DashboardAdapter(val context:Context,private val dataList:List<Data>,
        private var onItemClicked:((dataInfo:Data)->Unit)):RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    inner class DashboardViewHolder(private val binding: ItemViewBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(dataInfo:Data) = binding.apply {
            msgDate.text = dataInfo.date
            msgSpenttime.text = dataInfo.spentTime
            root.setOnClickListener {
                onItemClicked(dataInfo)
            }
        }

    }
}


