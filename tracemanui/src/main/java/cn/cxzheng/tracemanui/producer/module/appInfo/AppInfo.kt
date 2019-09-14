package com.ctrip.ibu.hotel.debug.server.producer.module.appInfo

import android.os.Build
import cn.cxzheng.tracemanui.utils.AppUtil
import com.ctrip.ibu.hotel.debug.server.producer.module.DebugBaseInfo

/**
 * Create by cxzheng on 2019/7/27
 */
class AppInfo : DebugBaseInfo() {

    val appInfo = ArrayList<String>().apply {
        add("机型：${Build.BRAND} ${Build.MODEL}")
        add("系统版本：${Build.VERSION.SDK_INT}")
//        add("包名：${Build.)}")
//        add("App版本：${AppUtil.getVersionCode()}")

    }
}