package com.example.login

import Network.loginModel.LoginResponseModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login.base.BaseViewModel
import com.example.login.ui.repository.LoginResponseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: LoginResponseRepository): BaseViewModel() {
    var disposables = CompositeDisposable()
    private val _loginResponse = MutableLiveData<LoginResponseModel>()
    val loginResponse :LiveData<LoginResponseModel>
    get() = _loginResponse

    fun sentLoginRequest(userEmail:String,password:String){
            val result = repository.getLoginResponse(userEmail,password)
            result.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverLoginResponse())
    }

    private fun getObserverLoginResponse():Observer<LoginResponseModel>{
        return object :Observer<LoginResponseModel>{
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
                showDialog.value = true
            }

            override fun onNext(t: LoginResponseModel) {
                _loginResponse.postValue(t)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {
                showDialog.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}