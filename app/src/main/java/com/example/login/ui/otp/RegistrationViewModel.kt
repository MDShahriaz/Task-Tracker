package com.example.login.ui.otp

import Network.RegistrationResponseModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.base.BaseViewModel
import com.example.login.ui.repository.RegistrationResponseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: RegistrationResponseRepository): BaseViewModel() {

    private val _registrationResponse = MutableLiveData<RegistrationResponseModel>()
    val registrationResponse :LiveData<RegistrationResponseModel> get() = _registrationResponse

    fun sent(email:String,otp:Int,password:String,sessionId:String){
        viewModelScope.launch {
            showDialog.value = true
            val result = repository.getRegistrationResponse(email, otp, password, sessionId)
            if(result.isSuccessful && result.body() != null){
                _registrationResponse.value = result.body()
                showDialog.value = false
            }
        }
    }
}