package com.example.login.ui.signup

import Network.RegistrationRequestModel
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.login.R
import com.example.login.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_signup) {
    private val viewModel: SignUpViewModel by viewModels()
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        emailFocusListener()
        viewModel.response.observe(viewLifecycleOwner) {
            sessionId = it.data?.sessionId ?: ""
            it.message?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
            val user = RegistrationRequestModel(
                binding.emailEditText.text.toString(),
                0,
                binding.confirmPasswordEditText.text.toString(),
                sessionId
            )
            val bundle = bundleOf("user" to user)
            Navigation.findNavController(view)
                .navigate(R.id.action_validationFragment_to_blankFragment, bundle)
        }
        binding.submitButton.setOnClickListener {
            if (!submitForm()) {
                val userEmail = binding.emailEditText.text.toString()
                viewModel.sentRequest(userEmail)

            }
        }
    }

    private fun submitForm(): Boolean {
        val validEmail = binding.emailContainer.helperText == null
        if (validEmail == null && binding.passwordEditText == binding.confirmPasswordEditText) {
            return true
        }
        return false
    }

    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

}