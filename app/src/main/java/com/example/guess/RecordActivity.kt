package com.example.guess

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.guess.data.GameDatabase
import com.example.guess.data.Record
import kotlinx.android.synthetic.main.activity_record2.*

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record2)
        val count = intent.getIntExtra("COUNTER", -1)
        counter_record.setText(count.toString())

        save.setOnClickListener { view ->
            val nickname = ed_nickname.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNT", count)
                .putString("REC_NICKNAME", nickname)
                .apply()
            // Room Insert
            val record = Record(nickname, count)
            Thread() {
                GameDatabase.getDatabase(this)?.recordDao()?.add(record)
            }.start()
            val intent = Intent()
            intent.putExtra("NICKNAME", nickname)
            setResult(RESULT_OK, intent)
            finish()
        }
        val score = getSharedPreferences("guess", MODE_PRIVATE)
            .getInt("REC_COUNT", -1)
        val nickname = getSharedPreferences("guess", MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d("", "data: $score / $nickname")

    }
}