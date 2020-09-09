package com.personal.eluniversal.injection.module

import com.google.gson.GsonBuilder
import com.personal.eluniversal.injection.network.ElUniversalAPI
import com.personal.eluniversal.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
@Suppress("unused")
object NetworkModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideElUniversalAPI(retrofit: Retrofit): ElUniversalAPI{
        return retrofit.create(ElUniversalAPI::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun providesRetrofitInterface(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder().addInterceptor(logging)

        val gson= GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(httpClient.build())
            .build()
    }
}