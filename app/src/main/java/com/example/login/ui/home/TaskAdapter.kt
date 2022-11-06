package com.example.login.ui.home

import android.app.TaskInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.databinding.FragmentAddTaskBinding
import com.example.login.databinding.TaskViewBinding
import com.example.login.taskcontrollernetwork.TaskData

class TaskAdapter(val tasks:List<TaskData>)
    :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(val binding:TaskViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(taskData: TaskData)=binding.apply{
            taskDescription.text = taskData.description
            if(taskData.spentHour <= 24)
                hour.text = taskData.spentHour.toString()+"h"
            if(taskData.spentMinute < 10){
                minute.text = "0${taskData.spentMinute.toString()}m"
            }
            else{
                minute.text = taskData.spentMinute.toString()+"m"
            }
        }

    }

}

