package com.example.login.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.TokenManager
import com.example.login.base.BaseFragment
import com.example.login.base.BaseViewModel
import com.example.login.databinding.FragmentDashboardBinding
import com.example.login.databinding.FragmentDashboardDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardDetailFragment : BaseFragment<DashboardDetailViewModel,FragmentDashboardDetailBinding>() {
    override val mViewModel: DashboardDetailViewModel by viewModels()
    override fun getViewBinding(): FragmentDashboardDetailBinding = FragmentDashboardDetailBinding.inflate(layoutInflater)
    private lateinit var tokenManager : TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(requireContext())
        val token = tokenManager.getToken()
        val Id = arguments?.getInt("id")
        if (Id != null) {
            mViewModel.getTaskModel(token.toString(),Id)
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.taskDetailResponse.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            mViewBinding?.taskDetailRecycleView?.adapter = TaskDetailAdapter(requireContext(),it.data)
        }
        mViewBinding?.taskDetailRecycleView?.layoutManager = LinearLayoutManager(requireContext())
    }

}