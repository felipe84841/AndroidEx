package com.desiglo.jokenpo.di

import android.content.Context
import com.desiglo.jokenpo.UI.main.MainViewModel
import com.desiglo.jokenpo.UI.rank.RankViewModel
import com.desiglo.jokenpo.api.Interceptor.AuthInterceptor
import com.desiglo.jokenpo.api.JokenpoService
import com.desiglo.jokenpo.repository.JokenpoRespository
import com.desiglo.jokenpo.repository.JokenpoRespositoryImpl
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder



val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get()).create(JokenpoService::class.java)}
    single { createPicassoAuth(get(), get())}
}

val respositoryModule = module {
    single<JokenpoRespository> { JokenpoRespositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { RankViewModel(get()) }
}

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://gamestjd.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}