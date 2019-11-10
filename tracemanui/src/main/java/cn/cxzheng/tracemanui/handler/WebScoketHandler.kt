package com.ctrip.ibu.hotel.debug.server.handler

import cn.cxzheng.tracemanui.MethodTraceServerManager.isActiveTraceMan
import cn.cxzheng.tracemanui.utils.LogUtil
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
                LogUtil.i("接收到消息：传输设备基本信息")
                DataProducer.producerAppInfo(AppInfo())
            }
            "StartMethodCost" -> {
                LogUtil.i("接收到消息：开始方法耗时统计")
                isActiveTraceMan = true
                MethodCostHelper.startMethodCost()
            }
            "EndMethodCost" -> {
                LogUtil.i("接收到消息：结束方法耗时统计")
                isActiveTraceMan = false
                MethodCostHelper.endMethodCost()
            }

        }


        LogUtil.detail("接到浏览器消息内容:$message")
    }
}