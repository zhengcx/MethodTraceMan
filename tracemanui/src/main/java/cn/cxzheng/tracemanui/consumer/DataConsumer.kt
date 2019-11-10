package com.ctrip.ibu.hotel.debug.server.consumer

import android.util.Log
import cn.cxzheng.tracemanui.MethodTraceServerManager
import cn.cxzheng.tracemanui.MethodTraceServerManager.APPINFO
import cn.cxzheng.tracemanui.MethodTraceServerManager.METHODCOST
import cn.cxzheng.tracemanui.TraceManServer
import cn.cxzheng.tracemanui.utils.LogUtil
import com.ctrip.ibu.hotel.debug.server.model.Message
import com.ctrip.ibu.hotel.debug.server.producer.module.appInfo.AppInfoProducer
import com.ctrip.ibu.hotel.debug.server.producer.module.methodcost.MethodCostProducer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Create by cxzheng on 2019/7/7
 * 数据消费
 */
class DataConsumer(var server: TraceManServer) {

    private var compositeDisposables: CompositeDisposable = CompositeDisposable()


    fun observe() {

        compositeDisposables.addAll(
            appInfoConsumer(),
            methodCostConsumer()
        )

    }

    private fun methodCostConsumer(): Disposable? {
        return MethodTraceServerManager.getModule<MethodCostProducer>(METHODCOST).subject()
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())
            .subscribe({
                val messageEntity = Message(METHODCOST, it)
                val message = messageEntity.toString()
                server.sendMessage(message)

                //Log输出
                LogUtil.i("已向浏览器发送${it.size}条方法耗时信息")
                it.forEach { methodInfo ->
                    val threadText = if (methodInfo.isMainThread) "[主线程]" else "[非主线程]"
                    LogUtil.detail("方法耗时详情:" + methodInfo.name + "  " + methodInfo.costTime + "ms" + " " + threadText)
                }
                LogUtil.detail(message)
            }, {
                LogUtil.detail(it.message)
            })
    }


    private fun appInfoConsumer(): Disposable? {
        return MethodTraceServerManager.getModule<AppInfoProducer>(APPINFO).subject()
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())
            .subscribe({
                val messageEntity = Message(APPINFO, it)
                val message = messageEntity.toString()
                server.sendMessage(message)
                LogUtil.detail(message)
            }, {
                LogUtil.detail(it.message)
            })
    }

    fun clearObserve() {
        compositeDisposables.dispose()
    }
}