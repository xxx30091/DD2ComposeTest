package com.example.dd2composetest

import android.app.Application
import com.example.dd2composetest.data.repository.UserRepository
import com.example.dd2composetest.data.service.GET_API_DOMAIN
import com.example.dd2composetest.utils.TokenInterceptor
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

@HiltAndroidApp
class ThisApp : Application() {

    // Depends on the flavor,
//    val stylishRepository: StylishRepository
//        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: ThisApp by Delegates.notNull()

        private val client: OkHttpClient = OkHttpClient()
            .newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60,  TimeUnit.SECONDS)
            .readTimeout(60,  TimeUnit.SECONDS)
            .addInterceptor(TokenInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        private val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl(GET_API_DOMAIN)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userRepository = UserRepository(retrofit)
//        val topicRepository = UserRepository(retrofit)
//        val videoRepository = UserRepository(retrofit)
//        val articleRepository = UserRepository(retrofit)
//        val searchRepository = UserRepository(retrofit)
//        val paymentRepository = UserRepository(retrofit)
//        val promoteRepository = UserRepository(retrofit)

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}