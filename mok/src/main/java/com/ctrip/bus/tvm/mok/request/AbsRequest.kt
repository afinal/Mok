package com.ctrip.bus.tvm.mok.request

import com.ctrip.bus.tvm.mok.Mok
import com.ctrip.bus.tvm.mok.api.ApiService
import com.ctrip.bus.tvm.mok.convert.Converter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

abstract class AbsRequest<T, R : AbsRequest<T, R>>(var url: String) : Request<T> {

    private var connectionTimeOut: Long = Mok.initialConfig.connectionTimeOut
    private var retrofitClient: Retrofit = Mok.globalRetrofit
    private var okHttpClient: OkHttpClient = Mok.okHttpClient
    internal var headers: MutableMap<String, String> = mutableMapOf()
    var converter: Converter<T>? = null

    init {
        headers(Mok.initialConfig.commonHeaders)
    }

    fun connectionTimeOut(connectionTimeOut: Long): R {
        this.connectionTimeOut = connectionTimeOut
        okHttpClient = okHttpClient.newBuilder()
            .connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
            .readTimeout(connectionTimeOut, TimeUnit.SECONDS)
            .writeTimeout(connectionTimeOut, TimeUnit.SECONDS)
            .build()
        retrofitClient = retrofitClient.newBuilder().client(okHttpClient).build()
        return this as R
    }

    fun headers(mutableMap: MutableMap<String, String>): R {
        headers.putAll(mutableMap)
        return this as R
    }

    fun header(key: String, value: String): R {
        headers[key] = value
        return this as R
    }

    fun getApiService(): ApiService {
        return retrofitClient.create(ApiService::class.java)
    }

    fun converter(converter: Converter<T>): R {
        this.converter = converter
        return this as R
    }
}