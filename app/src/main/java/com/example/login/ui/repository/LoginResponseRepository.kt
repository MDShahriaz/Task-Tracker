package com.example.login.ui.repository

import Network.ApIInterface
import Network.loginModel.LoginRequestModel
import Network.loginModel.LoginResponseModel
import android.util.Log
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import io.reactivex.Observable

class LoginResponseRepository @Inject constructor(private val apiInterface:ApIInterface){
    fun getLoginResponse(userEmail:String,password:String): Observable<LoginResponseModel> {
        val obj = LoginRequestModel(userEmail, password)
        val result = apiInterface.sentLoginInfo(obj)
        return result
    }
}