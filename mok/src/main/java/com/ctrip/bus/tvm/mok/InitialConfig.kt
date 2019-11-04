package com.ctrip.bus.tvm.mok

class InitialConfig private constructor() {

    lateinit var baseUrl: String
    var commonHeaders: MutableMap<String, String> = mutableMapOf()
    var connectionTimeOut: Long = HttpConfig.DEFAULT_CONNECTION_TIME_OUT


    open class Builder {

        private var initialConfig: InitialConfig = InitialConfig()

        fun baseUrl(baseUrl: String): Builder {
            initialConfig.baseUrl = baseUrl
            return this
        }

        fun commonHeaders(commonHeaders: MutableMap<String, String>): Builder {
            initialConfig.commonHeaders = commonHeaders
            return this
        }

        fun connectionTimeOut(connectionTimeOut: Long): Builder {
            initialConfig.connectionTimeOut = connectionTimeOut
            return this
        }

        fun build(): InitialConfig {
            return initialConfig
        }
    }
}