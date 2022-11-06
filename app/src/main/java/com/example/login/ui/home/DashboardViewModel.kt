package com.example.login.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.login.base.BaseViewModel
import com.example.login.ui.repository.DashboardTaskDetailRepository
import com.example.login.taskcontrollernetwork.getmodel.GetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: DashboardTaskDetailRepository):BaseViewModel() {
    var disposables = CompositeDisposable()
    val dashboardResponse = MutableLiveData<GetModel>()
    fun getDashBoard(token:String){
        val result = repository.getGetModel()
        result.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserverDashboardResponse())
    }

    private fun getObserverDashboardResponse(): Observer<GetModel> {
        return object:Observer<GetModel>{
            override fun onSubscribe(d: Disposable) {
                showDialog.value = true
                disposables.add(d)
            }

            override fun onNext(t: GetModel) {
                dashboardResponse.postValue(t)
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