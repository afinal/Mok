package com.ctrip.bus.tvm.mok.convert

import io.reactivex.functions.Function
import okhttp3.ResponseBody

abstract class Converter<T>: Function<ResponseBody, T>