package com.example.login.ui.repository

import Network.ApIInterface
import Network.RegistrationRequestModel
import Network.RegistrationResponseModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.Response
import javax.inject.Inject

class RegistrationResponseRepository @Inject constructor(private val apIInterface: ApIInterface){

    suspend fun getRegistrationResponse(email:String,otp:Int,password:String,sessionId:String): retrofit2.Response<RegistrationResponseModel> {
        val obj = RegistrationRequestModel(email,otp,password,sessionId)
        val result = apIInterface.sentRegisterInfo(obj)
        return result
    }
}