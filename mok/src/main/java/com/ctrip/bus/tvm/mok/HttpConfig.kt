package com.ctrip.bus.tvm.mok

/**
 * http config
 * @author max
 * @property MAX_CONNECTION_TIME_OUT max time out
 * @property MIN_CONNECTION_TIME_OUT min time out
 * @property DEFAULT_CONNECTION_TIME_OUT default time out
 */
object HttpConfig {

    const val MAX_CONNECTION_TIME_OUT = 120L

    const val DEFAULT_CONNECTION_TIME_OUT = 60L

    const val MIN_CONNECTION_TIME_OUT = 10L
}