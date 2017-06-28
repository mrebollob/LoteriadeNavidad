/*
 * Copyright (c) 2017. Manuel Rebollo Báez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mrebollob.loteriadenavidad.domain.interactor

import com.mrebollob.loteriadenavidad.domain.executor.PostExecutionThread
import com.mrebollob.loteriadenavidad.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class AbstractInteractor<T, Params> constructor(private val threadExecutor: ThreadExecutor,
                                                         private val postExecutionThread: PostExecutionThread) {

    private var disposables = CompositeDisposable()

    internal abstract fun buildInteractorObservable(params: Params): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params) {

        if (disposables.isDisposed) {
            disposables = CompositeDisposable()
        }

        val observable = buildInteractorObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}