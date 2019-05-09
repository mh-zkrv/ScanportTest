package com.zakriev.scanporttest.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import com.zakriev.scanporttest.DEBUG_TAG
import com.zakriev.scanporttest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]


        btn.setOnClickListener {
            viewModel.saveData(etSurname.text.toString(), etName.text.toString(), etPatronymic.text.toString())
        }

        viewModel.message.observe(this, Observer {
            it?.let {
                Snackbar.make(rootLayout, it, Snackbar.LENGTH_LONG).show()
                viewModel.snackbarWasShown()
            }
        })


        // for debugging
        viewModel.getClients().observe(this, Observer {
            Log.d(DEBUG_TAG, it.toString())
        })



    }
}
