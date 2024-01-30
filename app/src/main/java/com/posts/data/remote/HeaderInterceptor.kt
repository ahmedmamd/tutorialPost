package com.posts.data.remote

import android.os.Build
import com.posts.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.jetbrains.annotations.NotNull
import java.io.IOException
import javax.inject.Inject

class HeaderInterceptor : Interceptor {
    @Inject
    constructor()

    val ANDROID = "0"

    @NotNull
    @Throws(IOException::class)
    override fun intercept(@NotNull chain: Interceptor.Chain): Response {
        return addRequestHeaders(chain)
    }

    private fun addRequestHeaders(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val originalHttpUrl = origin.url
        val url = originalHttpUrl.newBuilder()
            .build()
        var request: Request? = null
        var builder: Request.Builder? = null
        builder = chain.request().newBuilder()
            .url(url)
            .addHeader("OSVersion", Build.VERSION.RELEASE.toString())
            .addHeader("AppVersion", BuildConfig.VERSION_NAME)
            .addHeader("MachineType", Build.BRAND + " " + Build.MODEL)
            .addHeader("OSType", ANDROID)
            .addHeader("Latitude", "0")
            .addHeader("Longitude", "0")
            .addHeader("Accept", "application/json")
        try {
            request = builder.build()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return chain.proceed(request!!)
    }



}