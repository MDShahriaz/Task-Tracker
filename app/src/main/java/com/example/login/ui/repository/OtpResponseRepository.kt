package com.example.login.ui.repository

import Network.ApIInterface
import Network.otpmodel.DataOTP
import Network.otpmodel.OtpRequestModel
import com.example.login.taskcontrollernetwork.TaskTrackerBaseResponse
import retrofit2.Response
import javax.inject.Inject

class OtpResponseRepository  @Inject constructor(private val apIInterface: ApIInterface){
    suspend fun getOtpResponse(email:String): Response<TaskTrackerBaseResponse<DataOTP>> {
        val obj =  OtpRequestModel(email)
        val result = apIInterface.setGenerateOTP(obj)
        return result
    }
}