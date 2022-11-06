package com.example.login.ui.home

import Network.ServiceBuilder.buildService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.base.BaseViewModel
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostModel
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostResponseModel
import kotlinx.coroutines.launch
import TaskApIInterface
import com.example.login.ui.repository.AddTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(private val repository: AddTaskRepository): BaseViewModel(){
    val addTaskResponse = MutableLiveData<TaskPostResponseModel>()
    fun addTask(token:String,taskPostModel: TaskPostModel){
        showDialog.value = true
        viewModelScope.launch {
            addTaskResponse.value = repository.getTaskPostResponseModel(taskPostModel)
            showDialog.value = false
        }
    }
}