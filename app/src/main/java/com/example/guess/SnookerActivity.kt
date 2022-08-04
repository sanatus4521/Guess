package com.example.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.guess.data.EventItem
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.coroutines.CoroutineContext

class SnookerActivity : AppCompatActivity() {
    val TAG = SnookerActivity::class.java.simpleName
    lateinit var job: Job
    lateinit var viewModel: SnookerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snooker)
//        job = Job()

        viewModel = ViewModelProvider(this).get(SnookerViewModel::class.java)
        viewModel.events.observe(this, Observer { events ->
            Log.d(TAG, "on Create: ${events.size}")

        })

//        launch {
//            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
//            val gson = Gson().fromJson(data, EventItem::class.java)
//            gson.forEach {
//                println(it)
//            }
//        }

    }

//    override val coroutineContext: CoroutineContext
//        get() = job + Dispatchers.IO
//
//    override fun onDestroy() {
//        super.onDestroy()
//        job.cancel()
//    }

}