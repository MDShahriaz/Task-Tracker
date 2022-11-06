package com.example.login.ui.repository

import Network.ApIInterface
import com.example.login.taskcontrollernetwork.taskdetailsmodel.TaskDetailResponseModel
import javax.inject.Inject

class DashboardDetailRepository @Inject constructor(private val api:ApIInterface){
    suspend fun getTaskDetailResponseModel(number:Int): TaskDetailResponseModel {
        val result = api.getTaskDetail(number)
        return result
    }
}