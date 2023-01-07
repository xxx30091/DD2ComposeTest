package com.example.dd2composetest

import android.app.Application
import kotlin.properties.Delegates

class ThisApp : Application() {

    // Depends on the flavor,
//    val stylishRepository: StylishRepository
//        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: ThisApp by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}