package com.ctrip.ibu.hotel.debug.server.handler

/**
 * Create by cxzheng on 2019/7/7
 */
interface IHttpRequestHandler {

    fun handle(path: String): Map<String, String?>
}