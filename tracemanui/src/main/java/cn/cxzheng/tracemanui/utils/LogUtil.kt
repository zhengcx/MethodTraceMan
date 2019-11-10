package cn.cxzheng.tracemanui.utils

import android.util.Log
import cn.cxzheng.tracemanui.MethodTraceServerManager
import cn.cxzheng.tracemanui.MethodTraceServerManager.DEBUG_SERVER_TAG
import cn.cxzheng.tracemanui.MethodTraceServerManager.MTM_LOG_DETAIL

/**
 * Create by cxzheng on 2019-11-09
 */
class LogUtil {

    companion object {


        @JvmStatic
        fun detail(message: String?) {
            if (MethodTraceServerManager.logLevel == MTM_LOG_DETAIL) {
                Log.i(DEBUG_SERVER_TAG, message)
            }
        }

        @JvmStatic
        fun i(message: String?) {
            Log.i(DEBUG_SERVER_TAG, message)
        }


        @JvmStatic
        fun e(message: String?) {
            Log.e(DEBUG_SERVER_TAG, message)
        }
    }
}