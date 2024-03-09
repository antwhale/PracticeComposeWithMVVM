package io.github.antwhale.compose.api

import io.github.antwhale.compose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AppInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("X-Master-Key", BuildConfig.API_KEY)
            .build()

        proceed(newRequest)
    }
}
