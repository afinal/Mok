package com.ctrip.bus.tvm.mok.request

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GetRequest<T>(url: String): AbsRequest<T, GetRequest<T>>(url) {

    private var queryMap: MutableMap<String, Any> = mutableMapOf()

    override fun execute(): Observable<T> {
        return getApiService().get(url, headerMap = headers, queryMap = queryMap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(converter)
    }

    fun setQueryMap(queryMap: MutableMap<String, Any>): GetRequest<T> {
        this.queryMap = queryMap
        return this
    }

    fun addParameter(key: String, value: Any): GetRequest<T> {
        this.queryMap[key] = value
        return this
    }
}