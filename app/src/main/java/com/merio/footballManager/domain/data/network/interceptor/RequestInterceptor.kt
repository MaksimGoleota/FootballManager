package com.merio.footballManager.domain.data.network.interceptor

import android.app.Application
import android.webkit.WebSettings
import com.merio.footballManager.FMApplication
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class RequestInterceptor(
    private val apiKey: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("apikey", apiKey)
            .build()

        val requestBuilder = originalRequest.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
