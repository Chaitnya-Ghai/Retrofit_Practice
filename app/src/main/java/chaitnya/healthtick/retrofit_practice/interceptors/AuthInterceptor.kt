package chaitnya.healthtick.retrofit_practice.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    val tokenManager =" TokenManagerClass"

    // this method observe our request and manipulate/intercept the request
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()//new obj of req.
        val token=" $tokenManager :   tokenManager.getToken() "
        request.addHeader("Authorization" , "Bearer $token")//adding a header to our request

        return chain.proceed(request.build())
    }
}