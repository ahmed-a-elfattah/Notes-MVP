package com.ahmed.aelfattah.notesmvp

import android.app.Application
import com.ahmed.aelfattah.notesmvp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@NoteApplication)
            modules(appModules)
        }
    }
}