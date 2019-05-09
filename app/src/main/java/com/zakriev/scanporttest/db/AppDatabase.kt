package com.zakriev.scanporttest.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Client::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clientDao() : ClientDao
}