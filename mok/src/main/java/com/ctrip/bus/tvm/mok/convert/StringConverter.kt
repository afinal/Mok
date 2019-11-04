package com.ctrip.bus.tvm.mok.convert

import okhttp3.ResponseBody

class StringConverter: Converter<String>() {
    override fun apply(t: ResponseBody): String {
        return t.string()
    }
}