package cn.cxzheng.tracemanui.utils

import com.google.gson.GsonBuilder

/**
 * Create by cxzheng on 2019/9/13
 */
class JsonUtil {

    companion object {
        fun toJson(any: Any?, excludeFieldsWithoutExposeAnnotation: Boolean): String {
            return any?.let {
                createGsonBuilder(excludeFieldsWithoutExposeAnnotation).create().toJson(any)
            } ?: ""
        }

        private fun createGsonBuilder(excludeFieldsWithoutExposeAnnotation: Boolean): GsonBuilder {
            return GsonBuilder().apply {
                if (excludeFieldsWithoutExposeAnnotation) {
                    excludeFieldsWithoutExposeAnnotation()
                }
                disableHtmlEscaping()
            }
        }
    }

}