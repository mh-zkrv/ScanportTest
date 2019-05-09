package com.zakriev.scanporttest.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ClientDao {
    @Insert
    fun saveClient(client: Client)

    @Query("SELECT * FROM Clients")
    fun getAll(): LiveData<List<Client>>
}