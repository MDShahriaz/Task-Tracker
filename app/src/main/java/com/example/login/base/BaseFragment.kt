package com.example.login.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.login.ProgressBarFragment

abstract class BaseFragment<VM:BaseViewModel,VB:ViewBinding> : Fragment() {
    val dialog = ProgressBarFragment()
    protected abstract val mViewModel:VM
    protected lateinit var mViewBinding:VB
    abstract fun getViewBinding():VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        return mViewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.showDialog.observe(viewLifecycleOwner){
            if(it){
                dialog.show(requireActivity().supportFragmentManager,"Custom Dialog")
            }else{
                dialog.dismiss()
            }
        }
    }

}