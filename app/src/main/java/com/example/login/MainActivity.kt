package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.viewModels
import com.example.login.Constant.USER_EMAIL
import com.example.login.base.BaseActivity
import com.example.login.base.BaseViewModel
import com.example.login.databinding.ActivityMainBinding
import com.example.login.ui.home.HomeActivity
import com.example.login.ui.signup.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(){
    override val mViewModel:MainViewModel by viewModels()
    private var mIsShowPass = false
    private lateinit var  tokenManager : TokenManager
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        tokenManager = TokenManager(application)
        textLogIn.setColors(R.color.gradient1,R.color.gradient3)
        mViewBinding.imagePass.setOnClickListener{
            mIsShowPass = !mIsShowPass
            showPassword(mIsShowPass)
        }
        showPassword(mIsShowPass)
        val prefs1 = getSharedPreferences(
            "Email_Info",
            Context.MODE_PRIVATE
        )
        mViewModel.loginResponse.observe(this){
            tokenManager.saveToken(it.data.token)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        mViewBinding.btnLogin.setOnClickListener{
            val userEmail = mViewBinding.userEmailId.text.toString()
            val password = mViewBinding.password.text.toString()
            if(mViewBinding.checkBox.isChecked){
                val editor = prefs1.edit()
                editor.putString(USER_EMAIL,mViewBinding.userEmailId.text.toString()).apply()
            }
            mViewModel.sentLoginRequest(userEmail,password)
        }
        mViewBinding.signUpButton.setOnClickListener{
            switchToSignUpActivity()
        }
    }

    private fun switchToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }



    override fun onResume() {
        super.onResume()
        val prefs1 = getSharedPreferences(
            "Email_Info",
            Context.MODE_PRIVATE
        )
        val emailAddress: String? = prefs1.getString(USER_EMAIL,null)
        mViewBinding.userEmailId.setText(emailAddress)
    }

    private fun showPassword(isShow:Boolean){
        if(isShow)
        {
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            imagePass.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        }
        else{
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            imagePass.setImageResource(R.drawable.ic_baseline_visibility_24)
        }
        password.setSelection(password.text.toString().length)
    }

}



