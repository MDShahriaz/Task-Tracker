package Network

import com.example.login.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val okHttpClientBuilder = OkHttpClient.Builder()
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private lateinit var retrofit: Retrofit
    private fun createRetrofit(token: String?) {
        if (token != null) {
            okHttpClientBuilder.addInterceptor {
                val request =
                    it.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                it.proceed(request)
            }
        }
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.addInterceptor(logging).build())
            .build()
    }

    fun <T> buildService(service: Class<T>, token: String? = null): T {
        createRetrofit(token)
        return retrofit.create((service))
    }

}