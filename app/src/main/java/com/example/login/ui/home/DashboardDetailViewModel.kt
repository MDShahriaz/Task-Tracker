package com.example.login.ui.home

import Network.ServiceBuilder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.base.BaseViewModel
import com.example.login.taskcontrollernetwork.taskdetailsmodel.TaskDetailResponseModel
import kotlinx.coroutines.launch
import TaskApIInterface
import com.example.login.ui.repository.DashboardDetailRepository
import com.example.login.ui.repository.DashboardTaskDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardDetailViewModel @Inject constructor(private val repository: DashboardDetailRepository):BaseViewModel() {
    val taskDetailResponse = MutableLiveData<TaskDetailResponseModel>()
    fun getTaskModel(token:String,number:Int){
        showDialog.value = true
        viewModelScope.launch {
            taskDetailResponse.value = repository.getTaskDetailResponseModel(number)
            showDialog.value = false
        }
    }
}