package com.zakriev.scanporttest.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.zakriev.scanporttest.R
import com.zakriev.scanporttest.db.Client
import com.zakriev.scanporttest.db.ClientDao
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.concurrent.Executor

const val FILENAME = "1.txt"

class MainRepository(private val context: Context, private val clientDao: ClientDao, private val ioExecutor: Executor) {

    fun saveData(surname: String, name: String, patronymic: String, message: MutableLiveData<String>) {
        ioExecutor.execute {
            try {
                if (setOf(surname, name, patronymic).contains("")) {
                    message.postValue(context.getString(R.string.empty_inputs))
                    return@execute
                }
                val fullString = fullNameToString(surname, name, patronymic)
                saveToFile(fullString)
                saveFromFileToDb()
                message.postValue(context.getString(R.string.success))
            } catch (e: Throwable) {
                message.postValue(context.getString(R.string.error))
            }
        }
    }


    private fun saveToFile(fullString: String) {
        val file = File(
            context.filesDir,
            FILENAME
        )
        FileWriter(file).use {
            it.write(fullString)
        }
    }

    private fun saveFromFileToDb() {
        val file = File(
            context.filesDir,
            FILENAME
        )
        val fullString = FileReader(file).readText()
        val client = fullStringToClient(fullString)
        clientDao.saveClient(client)
    }

    private fun fullNameToString(surname: String, name: String, patronymic: String) : String {
        return "$surname;$name;$patronymic"
    }

    private fun fullStringToClient(fullString: String) : Client {
        val wordsList = fullString.split(";")
        val surname = wordsList[0]
        val name = wordsList[1]
        return Client(surname, name)
    }

    // for debug
    fun getClients() : LiveData<List<Client>> {
        return clientDao.getAll()
    }
}