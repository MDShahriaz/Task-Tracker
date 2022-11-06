package com.example.login.taskcontrollernetwork.taskpostmodel

import com.example.login.taskcontrollernetwork.TaskData

data class TaskPostModel(
    val date: String,
    val taskDetails: List<TaskData>
)