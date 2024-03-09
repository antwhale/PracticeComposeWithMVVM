package io.github.antwhale.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.antwhale.compose.api.AppInterceptor
import io.github.antwhale.compose.api.TweetsyAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder().baseUrl("https://api.jsonbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideTweetsyAPI(retrofit: Retrofit) : TweetsyAPI {
        return retrofit.create(TweetsyAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(appInterceptor: AppInterceptor) : OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(appInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAppInterceptor() = AppInterceptor()

}