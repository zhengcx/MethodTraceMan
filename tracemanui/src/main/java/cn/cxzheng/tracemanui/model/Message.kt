package com.ctrip.ibu.hotel.debug.server.model

import cn.cxzheng.tracemanui.utils.JsonUtil

/**
 * Create by cxzheng on 2019/7/8
 * 客户端传输的消息结构
 */
class Message {

    val SUCCESS = 1
    val DEFAULT_FAIL = 0

    var code: Int = 0
    var message: String
    var data: DataWithName? = null


    constructor(errorMessage: String) {
        this.code = DEFAULT_FAIL
        this.message = errorMessage
        this.data = null
    }

    constructor(moduleName: String, data: Any) {
        this.code = SUCCESS
        this.message = "success"
        this.data = DataWithName(moduleName, data)
    }

    class DataWithName(var moduleName: String, var payload: Any)

    override fun toString(): String {
        return JsonUtil.toJson(this, false)
    }
}