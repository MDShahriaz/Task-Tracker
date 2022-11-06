package com.example.login

import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import com.example.login.Constant.PREFS_TOKEN_FILE
import com.example.login.Constant.USER_TOKEN

class TokenManager(context: Context){

    private val prefs = context.getSharedPreferences(
        PREFS_TOKEN_FILE,
        Context.MODE_PRIVATE
    )
    fun saveToken(token:String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun getToken(): String ?{
        return prefs.getString(USER_TOKEN,null)
    }
}


