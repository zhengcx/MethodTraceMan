package com.ctrip.ibu.hotel.debug.server.handler

import android.content.Context
import android.content.res.AssetManager
import android.text.TextUtils
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * Create by cxzheng on 2019/7/7
 */
class HttpRequestHandler : IHttpRequestHandler {

    private var assetManager: AssetManager
    private var fileName: String

    constructor(context: Context, fileName: String) {
        assetManager = context.resources.assets
        this.fileName = fileName
    }

    override fun handle(path: String): Map<String, String?> {
//        Log.i(DEBUG_SERVER_TAG, path)
        var resultPath: String? = null
        if (path.startsWith("/")) {
            resultPath = path.substring(1)
        }
        if (TextUtils.isEmpty(resultPath)) {
            resultPath = "debugbridge/index.html"
        }
        val resourcePath = "$fileName/$resultPath"

        return mapOf("mimeType" to getMimeType(resourcePath), "payload" to getPayload(resourcePath))
    }


    /**
     * 资源类型
     */
    private fun getMimeType(fileName: String): String? {
        return when {
            TextUtils.isEmpty(fileName) -> null
            fileName.endsWith(".html") -> "text/html;charset=utf-8"
            fileName.endsWith(".js") -> "application/javascript;charset=utf-8"
            fileName.endsWith(".css") -> "text/css;charset=utf-8"
            else -> "application/octet-stream"
        }
    }

    /**
     * 获取资源
     */
    private fun getPayload(fileName: String): String {
        var input: InputStream? = null
        try {
            input = assetManager.open(fileName)

            val sb = StringBuilder()
            val br = BufferedReader(InputStreamReader(input, Charset.forName("UTF-8")))
            var line: String? = br.readLine()
            while (line != null) {
                sb.append("\n")
                sb.append(line)
                line = br.readLine()
            }
            return sb.toString()
        } finally {
            try {
                input?.close()
            } catch (e: Throwable) {
            }

        }
    }


}