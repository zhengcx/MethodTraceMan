package com.ctrip.ibu.hotel.debug.server.handler

import com.koushikdutta.async.http.WebSocket

/**
 * Create by cxzheng on 2019/7/7
 */
interface IWebScoketHandler {

    fun handle(webScoket: WebSocket, message: String?)
}