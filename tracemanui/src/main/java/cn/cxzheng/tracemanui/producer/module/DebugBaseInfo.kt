package com.ctrip.ibu.hotel.debug.server.producer.module

import org.joda.time.DateTime


/**
 * Create by cxzheng on 2019/7/27
 */
open class DebugBaseInfo {

    var timeStamp = DateTime.now().toString("yyyy-MM-dd HH:mm:ss")
}