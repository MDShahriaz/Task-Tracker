package com.example.login.di

import Network.ApIInterface
import android.content.Context
import com.example.login.Constant
import com.example.login.Constant.TOKEN
import com.example.login.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private lateinit var  tokenManager : TokenManager
    @Singleton
    @Provides
    fun providesRetrofit(@ApplicationContext context: Context):Retrofit{
        tokenManager = TokenManager(context)
        val token = tokenManager.getToken()
        val okHttpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        if(token!=null) {
            okHttpClientBuilder.addInterceptor {
                val request =
                    it.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                it.proceed(request)
            }
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientBuilder.addInterceptor(logging).build())
            .build()
        return retrofit
    }

    @Singleton
    @Provides
    fun providesAPIInterface(retrofit: Retrofit):ApIInterface{
        return retrofit.create(ApIInterface::class.java)
    }
}