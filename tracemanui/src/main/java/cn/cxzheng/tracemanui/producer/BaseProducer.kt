package com.ctrip.ibu.hotel.debug.server.producer

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Create by cxzheng on 2019/7/7
 */
open class BaseProducer<T> {

    private var mSubject: Subject<T> = PublishSubject.create()


    fun produce(data: T) {
        mSubject.onNext(data)
    }

    fun subject(): Observable<T> {
        return mSubject
    }

}