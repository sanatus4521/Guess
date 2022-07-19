package com.example.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        }
        val score = getSharedPreferences("guess", MODE_PRIVATE)
            .getInt("REC_COUNT", -1)
        val nickname = getSharedPreferences("guess", MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d("", "data: $score / $nickname")

    }
}