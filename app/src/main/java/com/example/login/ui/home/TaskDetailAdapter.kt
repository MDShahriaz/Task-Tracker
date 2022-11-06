package com.example.login.ui.home
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.databinding.TaskDetailViewBinding
import com.example.login.taskcontrollernetwork.taskdetailsmodel.Detail

class TaskDetailAdapter(context: Context,private val taskInfo:List<Detail>)
    : RecyclerView.Adapter<TaskDetailAdapter.TaskDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDetailViewHolder {
        val binding = TaskDetailViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskDetailViewHolder, position: Int) {
        holder.bind(taskInfo[position])
    }

    override fun getItemCount(): Int {
        return taskInfo.size
    }

    inner class TaskDetailViewHolder(val binding: TaskDetailViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(taskData: Detail)=binding.apply{
            information.text = taskData.description
            Id.text = taskData.id.toString()
            spentTimeHour.text = taskData.spentHour.toString()
            spentTimeMinute.text = taskData.spentMinute.toString()
            spentTime.text = taskData.spentTime
        }

    }
}