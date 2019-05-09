package com.zakriev.scanporttest

import android.app.Application
import android.arch.persistence.room.Room
import com.zakriev.scanporttest.db.AppDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

const val DB_NAME = "scanport_test.db"
const val DEBUG_TAG = "debug_app"

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var db: AppDatabase
        lateinit var ioExecutor: Executor
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ioExecutor = Executors.newSingleThreadExecutor()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DB_NAME).build()
    }
}