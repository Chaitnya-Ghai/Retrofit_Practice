package chaitnya.healthtick.retrofit_practice

import chaitnya.healthtick.retrofit_practice.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log full request and response
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor()) // Custom interceptor for headers/auth
        .addInterceptor(loggingInterceptor)// Logging interceptor
        .build()

    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getApiService(): ApiService {
        return getInstance().create(ApiService::class.java)
    }
}