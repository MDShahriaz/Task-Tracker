package com.example.login.ui.signup

import Network.otpmodel.DataOTP
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.base.BaseViewModel
import com.example.login.taskcontrollernetwork.TaskTrackerBaseResponse
import com.example.login.ui.repository.OtpResponseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val otpResponseRepository: OtpResponseRepository):
    BaseViewModel() {
    private val _response = MutableLiveData<TaskTrackerBaseResponse<DataOTP>>()
    val response : LiveData<TaskTrackerBaseResponse<DataOTP>> get() = _response
    fun sentRequest(userEmail:String){
        viewModelScope.launch {
            val result = callService {otpResponseRepository.getOtpResponse(userEmail)}
            result?.let {
                _response.value = it
            }
        }
    }
}