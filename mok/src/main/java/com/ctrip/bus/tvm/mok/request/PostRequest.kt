package com.ctrip.bus.tvm.mok.request

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class PostRequest<T>(url: String) : AbsRequest<T, PostRequest<T>>(url) {

    private var requestBody: RequestBody? = null
    private var parameters: MutableMap<String, Any> = mutableMapOf()

    override fun execute(): Observable<T> {
        return getApiService().post(url, headers, generateRequestBody())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(converter)
    }

    private fun generateRequestBody(): RequestBody? {
        if (requestBody == null) {
            uploadJson(Gson().toJson(parameters))
        }
        return requestBody
    }

    fun addParameter(key: String, value: Any): PostRequest<T> {
        parameters[key] = value
        return this
    }

    fun uploadJson(json: String): PostRequest<T> {
        this.requestBody = json.toRequestBody(JSON_CONTENT_TYPE)
        return this
    }

    companion object {
        val JSON_CONTENT_TYPE: MediaType = "application/json; charset=utf-8".toMediaType()
    }
}