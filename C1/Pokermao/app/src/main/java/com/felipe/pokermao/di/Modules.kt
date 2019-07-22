package com.felipe.pokermao.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.felipe.pokermao.api.PokemonService
import com.felipe.pokermao.api.interceptor.AuthInterceptor
import com.felipe.pokermao.repository.PokemonRepository
import com.felipe.pokermao.repository.PokemonRepositoryImpl
import com.felipe.pokermao.view.form.FormPokemonViewModel
import com.felipe.pokermao.view.list.ListPokemonViewModel
import com.felipe.pokermao.view.splash.SplashViewModel
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createOkhttpClientAuth(get()) }
    single { createNetworkClient(get()).create(PokemonService::class.java)}
    single { createPicassoAuth(get(), get())}
}

val respositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ListPokemonViewModel(get()) }
    viewModel { FormPokemonViewModel(get()) }
}

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://pokedexdx.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
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