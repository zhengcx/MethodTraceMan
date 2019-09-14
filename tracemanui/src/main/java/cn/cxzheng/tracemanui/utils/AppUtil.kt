package cn.cxzheng.tracemanui.utils

import android.content.Context
import android.content.pm.PackageInfo

class AppUtil {

    companion object {
        @Synchronized
        fun getAppName(context: Context): String? {
            try {
                val labelRes = getPackageInfo(context).applicationInfo.labelRes
                return context.resources.getString(labelRes)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        @Synchronized
        fun getVersionName(context: Context): String? {
            try {
                return getPackageInfo(context).versionName
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }


        @Synchronized
        fun getVersionCode(context: Context): Int {
            try {
                return getPackageInfo(context).versionCode
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return 0
        }


        @Synchronized
        fun getPackageName(context: Context): String? {
            try {
                return getPackageInfo(context).packageName
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        private fun getPackageInfo(context: Context): PackageInfo {
            return context.packageManager.getPackageInfo(
                context.packageName, 0
            )
        }
    }


}