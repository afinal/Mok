package com.ctrip.bus.tvm.mok.request

import io.reactivex.Observable

interface Request<T> {

    fun execute(): Observable<T>
}