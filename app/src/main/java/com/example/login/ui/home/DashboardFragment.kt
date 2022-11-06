package com.example.login.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.Constant.TOKEN
import com.example.login.R
import com.example.login.TokenManager
import com.example.login.base.BaseFragment
import com.example.login.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardViewModel,FragmentDashboardBinding>() {

    override val mViewModel: DashboardViewModel by viewModels()
    override fun getViewBinding(): FragmentDashboardBinding = FragmentDashboardBinding.inflate(layoutInflater)

    private lateinit var tokenManager : TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tokenManager = TokenManager(requireContext())
        val token = TOKEN
        mViewModel.getDashBoard(token)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.dashboardResponse.observe(viewLifecycleOwner){
            mViewBinding?.dataList?.adapter = DashboardAdapter(requireContext(),it.data){
                val bundle = bundleOf("id" to it.id)
                Navigation.findNavController(view).navigate(R.id.action_dashboardFragment_to_dashboardDetailFragment,bundle)
            }
            mViewBinding?.dataList?.layoutManager = LinearLayoutManager(requireContext())
            mViewBinding?.dataList?.startLayoutAnimation()
        }

        mViewBinding?.fabBtn?.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_dashboardFragment_to_addTaskFragment)
        }
    }
}