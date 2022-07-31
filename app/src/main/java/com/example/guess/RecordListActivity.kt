package com.example.guess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.guess.data.GameDatabase
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)

        // Recycler
        CoroutineScope(Dispatchers.IO).launch {
            var records = GameDatabase.getDatabase(this@RecordListActivity)?.recordDao()?.getAll()
            records?.let {
                runOnUiThread {
                    recycler_record_list.layoutManager = LinearLayoutManager(this@RecordListActivity)
                    recycler_record_list.setHasFixedSize(true)
                    recycler_record_list.adapter = RecordAdapter(it)
                }
            }
        }
//        Thread() {
//            var records = GameDatabase.getDatabase(this)?.recordDao()?.getAll()
//            records?.let {
//                runOnUiThread {
//                    recycler_record_list.layoutManager = LinearLayoutManager(this)
//                    recycler_record_list.setHasFixedSize(true)
//                    recycler_record_list.adapter = RecordAdapter(it)
//                }
//            }
//        }.start()
    }
}