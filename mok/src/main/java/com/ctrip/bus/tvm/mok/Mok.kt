package com.ctrip.bus.tvm.mok

import com.ctrip.bus.tvm.mok.request.GetRequest
import com.ctrip.bus.tvm.mok.request.PostRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author fetch manager
 */
object Mok {

    lateinit var initialConfig: InitialConfig
    lateinit var globalRetrofit: Retrofit
    lateinit var okHttpClient: OkHttpClient

    /**
     * init manager
     * @param initialConfig initial config
     */
    fun init(initialConfig: InitialConfig) {
        this.initialConfig = initialConfig
        this.okHttpClient = createOkHttpClient(initialConfig = initialConfig)
        globalRetrofit = Retrofit.Builder()
            .baseUrl(initialConfig.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * create ok http client
     * @param initialConfig initial config
     * @return ok http client
     */
    private fun createOkHttpClient(initialConfig: InitialConfig): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .writeTimeout(initialConfig.connectionTimeOut, TimeUnit.SECONDS)
            .readTimeout(initialConfig.connectionTimeOut, TimeUnit.SECONDS)
            .connectTimeout(initialConfig.connectionTimeOut, TimeUnit.SECONDS)
            .build()
    }

    fun <T> get(url: String): GetRequest<T> {
        return GetRequest(url)
    }

    fun <T> post(url: String): PostRequest<T> {
        return PostRequest(url)
    }
}