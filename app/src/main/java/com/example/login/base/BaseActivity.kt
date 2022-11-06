package com.example.login.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.login.ProgressBarFragment

abstract class BaseActivity<VM:BaseViewModel,VB:ViewBinding> : AppCompatActivity() {
    protected abstract val mViewModel:VM
    protected lateinit var mViewBinding:VB
    abstract fun getViewBinding(): VB

    private val dialog = ProgressBarFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        mViewModel.showDialog.observe(this){
            if(it){
                dialog.show(supportFragmentManager,"customDialog")
            }else{
                dialog.dismiss()
            }
        }
    }
}