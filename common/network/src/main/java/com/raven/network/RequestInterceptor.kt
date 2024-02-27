package com.raven.network

import com.raven.network.NetworkModule.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder().addQueryParameter("api-key", API_KEY).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }
}