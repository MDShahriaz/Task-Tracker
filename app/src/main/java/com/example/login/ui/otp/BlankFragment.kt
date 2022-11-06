package com.example.login.ui.otp

import Network.RegistrationRequestModel
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.login.MainActivity
import com.example.login.base.BaseFragment
import com.example.login.databinding.FragmentBlankBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlankFragment : BaseFragment<RegistrationViewModel,FragmentBlankBinding>() {

    override val mViewModel: RegistrationViewModel by viewModels()
    override fun getViewBinding(): FragmentBlankBinding  = FragmentBlankBinding.inflate(layoutInflater)


    private lateinit var user:RegistrationRequestModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {bundle ->
            if (bundle != null) {
                user = bundle.getParcelable("user")!!
            }
        }
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = user.email
        val id = user.sessionId
        val pass = user.password
        val otp = mViewBinding.otpEditText.text
        mViewBinding.submitOtp.setOnClickListener{
            mViewModel.sent(email,otp.toString().toInt(),pass,id)
            mViewModel.registrationResponse.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this@BlankFragment.requireContext(),MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}