package com.example.guess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.guess.data.Event
import com.example.guess.data.EventItem
import com.example.guess.data.GameDatabase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.URL
import kotlin.coroutines.CoroutineContext

class RecordListActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job:Job
    val TAG = RecordActivity::class.java.simpleName

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        job = Job()

        launch {
            Log.d("$TAG beforeDatabase","${Thread.currentThread().name}")
            var records = GameDatabase.getDatabase(this@RecordListActivity)?.recordDao()?.getAll()
            records?.let {
                recycler_record_list.layoutManager = LinearLayoutManager(this@RecordListActivity)
                recycler_record_list.setHasFixedSize(true)
                recycler_record_list.adapter = RecordAdapter(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}