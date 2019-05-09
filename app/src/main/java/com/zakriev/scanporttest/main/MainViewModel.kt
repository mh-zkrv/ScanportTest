package com.zakriev.scanporttest.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.zakriev.scanporttest.App
import com.zakriev.scanporttest.db.Client

class MainViewModel : ViewModel() {

    private val repository = MainRepository(App.instance, App.db.clientDao(), App.ioExecutor)

    var message = MutableLiveData<String>()

    private lateinit var clients: LiveData<List<Client>>

    // for debugging
    fun getClients() : LiveData<List<Client>> {
        if(!::clients.isInitialized) {
            clients = repository.getClients()
        }
        return clients
    }

    fun saveData(surname: String, name: String, patronymic: String) {
        repository.saveData(surname, name, patronymic, message)
    }

    fun snackbarWasShown() {
        message.value = null
    }
}