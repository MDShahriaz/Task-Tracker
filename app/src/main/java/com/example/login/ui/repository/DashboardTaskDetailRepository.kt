package com.example.login.ui.repository

import Network.ApIInterface
import com.example.login.taskcontrollernetwork.getmodel.GetModel
import io.reactivex.Observable
import javax.inject.Inject

class DashboardTaskDetailRepository @Inject constructor(private val api:ApIInterface) {
   fun getGetModel(): Observable<GetModel> {
        val result = api.getTask()
        return result
    }
}