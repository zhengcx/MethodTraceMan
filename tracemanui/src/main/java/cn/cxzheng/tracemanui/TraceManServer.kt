package cn.cxzheng.tracemanui

import android.util.Log
import cn.cxzheng.tracemanui.utils.LogUtil
import com.koushikdutta.async.AsyncServer
import com.koushikdutta.async.callback.CompletedCallback
import com.koushikdutta.async.http.WebSocket
import com.koushikdutta.async.http.server.AsyncHttpServer
import com.koushikdutta.async.http.server.AsyncHttpServerRequest
import com.koushikdutta.async.http.server.AsyncHttpServerResponse
import java.util.*

/**
 * Create by cxzheng on 2019/7/6
 * Server
 */
class TraceManServer {

    private var port: Int = 0
    private var server: AsyncHttpServer
    private var webSockets: MutableList<WebSocket>
    var serverCallback: ServerCallback? = null


    constructor(port: Int) {
        this.port = port
        server = AsyncHttpServer()
        webSockets = Collections.synchronizedList(ArrayList())

        //webScoket请求监听回调
        server.websocket("/refresh") { webSocket, _ ->
            webSockets.add(webSocket)

            webSocket.closedCallback = CompletedCallback { webSockets.remove(webSocket) }

            webSocket.stringCallback = WebSocket.StringCallback { s ->
                serverCallback?.onWebSocketRequest(webSocket, s)
            }
        }

        //Http请求监听回调
        server.get("/.*[(.html)|(.css)|(.js)|(.png)|(.jpg)|(.jpeg)|(.ico)]") { request, response ->
            serverCallback?.onHttpRequest(request, response)
            LogUtil.detail("接收到浏览器请求UI界面")
        }
    }

    /**
     * 启动服务
     */
    fun start() {
        server.listen(port)
    }

    /**
     * 关闭服务
     */
    fun stop() {
        server.stop()
        AsyncServer.getDefault().stop()
    }

    /**
     * 发送数据
     */
    fun sendMessage(message: String) {
        val wss = webSockets.toTypedArray()
        wss.forEach {
            if (it.isOpen) {
                it.send(message)
            }
        }
    }


    interface ServerCallback {
        fun onHttpRequest(request: AsyncHttpServerRequest, response: AsyncHttpServerResponse)

        fun onWebSocketRequest(webSocket: WebSocket, messageFromClient: String)
    }
}