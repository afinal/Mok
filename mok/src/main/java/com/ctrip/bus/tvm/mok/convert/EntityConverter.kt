package com.ctrip.bus.tvm.mok.convert

import com.google.gson.Gson
import okhttp3.ResponseBody
import java.lang.reflect.Type

class EntityConverter<T>(var clazz: Class<T>? = null, var type: Type? = null): Converter<T>() {
    override fun apply(t: ResponseBody): T {
        clazz?.let {
            return Gson().fromJson(t.string(), clazz)
        }
        type?.let {
            return Gson().fromJson<T>(t.string(), type)
        }
        throw IllegalStateException("both of clazz and type are be null")
    }
}