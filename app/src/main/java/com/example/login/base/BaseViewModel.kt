package com.example.login.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.taskcontrollernetwork.TaskTrackerBaseResponse
import retrofit2.Response
import java.lang.Exception

open class BaseViewModel: ViewModel() {
    val showDialog = MutableLiveData<Boolean>()

    protected val _showMessage = MutableLiveData<String>()
    suspend fun <T> callService(
        showSuccessMessage:Boolean = false,
        api: suspend() -> Response<T>
    ):T?{
        try{
            val response = api.invoke()
            if(response.code() == 401){
                _showMessage.value = "Authentication fail"
                return null
            }
            if(response.isSuccessful && response.body() is TaskTrackerBaseResponse<*>){
                val baseResponse = response.body() as TaskTrackerBaseResponse<*>
                if(showSuccessMessage){
                    baseResponse.message?.let {
                        if(it.isNotEmpty()){
                            _showMessage.value = it
                        }
                    }
                }
            }
            return response.body()
        }catch (e:Exception){
            _showMessage.value = "Something went wrong! Please try again later"
            e.printStackTrace()
        }
        return null
    }
}