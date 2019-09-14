package com.ctrip.ibu.hotel.debug.server.handler

import android.util.Log
import cn.cxzheng.tracemanui.MethodTraceServerManager.DEBUG_SERVER_TAG
import cn.cxzheng.tracemanui.MethodTraceServerManager.isActiveTraceMan
import com.ctrip.ibu.hotel.debug.server.producer.DataProducer
import com.ctrip.ibu.hotel.debug.server.producer.module.appInfo.AppInfo
import com.ctrip.ibu.hotel.debug.server.producer.module.methodcost.MethodCostHelper
import com.koushikdutta.async.http.WebSocket
import org.json.JSONObject

/**
 * Create by cxzheng on 2019/7/7
 */
class WebScoketHandler : IWebScoketHandler {

    override fun handle(webScoket: WebSocket, message: String?) {
        val obj = JSONObject(message)
        val moduleName = obj["moduleName"]

        when (moduleName) {
            "OnlineMessage" -> {
                isActiveTraceMan = true
                DataProducer.producerAppInfo(AppInfo())
            }
            "StartMethodCost" -> {
                MethodCostHelper.startMethodCost()
            }
            "EndMethodCost" -> {
                MethodCostHelper.endMethodCost()
            }

        }


        Log.i(DEBUG_SERVER_TAG, message)
    }
}