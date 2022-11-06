package com.example.login.ui.repository

import Network.ApIInterface
import TaskApIInterface
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostModel
import com.example.login.taskcontrollernetwork.taskpostmodel.TaskPostResponseModel
import javax.inject.Inject
import javax.inject.Named

class AddTaskRepository @Inject constructor(private val api: ApIInterface) {
    suspend fun getTaskPostResponseModel(taskPostModel: TaskPostModel): TaskPostResponseModel {
        val result = api.postTask(taskPostModel)
        return result
    }
}