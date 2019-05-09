package com.zakriev.scanporttest.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Clients")
data class Client(var surname: String, var name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}