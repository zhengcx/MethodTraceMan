package com.ctrip.ibu.hotel.debug.server.producer.module.methodcost

import cn.cxzheng.tracemanui.TraceMan
import com.ctrip.ibu.hotel.debug.server.producer.DataProducer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Create by cxzheng on 2019/9/9
 */
class MethodCostHelper {

    companion object {

        fun startMethodCost() {
            TraceMan.startCollectMethodCost()
        }


        fun endMethodCost() {
            val methodCostInfo = TraceMan.endCollectMethodCost()
            methodCostInfo?.let {
                Observable.create<List<MethodInfo>> {
                    it.onNext(methodCostInfo)
                    it.onComplete()

                }.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        DataProducer.producerMethodCostInfo(it)
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }
}