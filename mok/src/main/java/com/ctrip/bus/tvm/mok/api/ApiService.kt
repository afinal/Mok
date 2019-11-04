package com.ctrip.bus.tvm.mok.api

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {

    @POST
    fun post(@Url url: String, @HeaderMap headerMap: MutableMap<String, String>, @Body body: RequestBody?): Observable<ResponseBody>

    @GET
    fun get(@Url url: String, @HeaderMap headerMap: MutableMap<String, String>, @QueryMap queryMap: MutableMap<String, Any>): Observable<ResponseBody>
}