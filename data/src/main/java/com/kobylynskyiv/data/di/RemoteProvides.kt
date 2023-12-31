package com.kobylynskyiv.data.di

import com.kobylynskyiv.data.BuildConfig
import com.kobylynskyiv.data.api.FruitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteProvides {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient().build())
            .build()
    }


    private fun provideOkHttpClient(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return try {
            OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .header( "User-Agent", "android")
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
        } catch (e: Exception) {
            OkHttpClient.Builder()
        }
    }

    @Singleton
    @Provides
    fun provideFruitApi(retrofit: Retrofit): FruitApi {
        return retrofit.create(FruitApi::class.java)
    }


}