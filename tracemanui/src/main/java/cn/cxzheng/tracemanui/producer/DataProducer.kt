package com.ctrip.ibu.hotel.debug.server.producer

import cn.cxzheng.tracemanui.MethodTraceServerManager
import cn.cxzheng.tracemanui.MethodTraceServerManager.APPINFO
import cn.cxzheng.tracemanui.MethodTraceServerManager.METHODCOST
import cn.cxzheng.tracemanui.MethodTraceServerManager.isActiveTraceMan
import com.ctrip.ibu.hotel.debug.server.producer.module.appInfo.AppInfo
import com.ctrip.ibu.hotel.debug.server.producer.module.appInfo.AppInfoProducer
import com.ctrip.ibu.hotel.debug.server.producer.module.methodcost.MethodCostProducer
import com.ctrip.ibu.hotel.debug.server.producer.module.methodcost.MethodInfo

/**
 * Create by cxzheng on 2019/7/23
 */
class DataProducer {

    companion object {

        fun producerAppInfo(appInfo: AppInfo) {
            MethodTraceServerManager.getModule<AppInfoProducer>(APPINFO).produce(appInfo)
        }

        fun producerMethodCostInfo(methodCostInfo: List<MethodInfo>) {
            MethodTraceServerManager.getModule<MethodCostProducer>(METHODCOST)
                .produce(methodCostInfo)
        }
    }
}