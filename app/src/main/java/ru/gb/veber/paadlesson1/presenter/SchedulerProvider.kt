package ru.gb.veber.paadlesson1.presenter

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.gb.veber.paadlesson1.presenter.ISchedulerProvider

//In the sake of testing
class SchedulerProvider : ISchedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()
}
