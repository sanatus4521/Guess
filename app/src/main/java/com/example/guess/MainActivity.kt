package com.example.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Secret number is: " + secretNumber.secret)
    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
        Log.d("MainActivity", "number: " + n)
        val validate = secretNumber.validate(n)
        val message:String
        if (validate > 0) {
            message = "bigger"
        } else if (validate < 0) {
            message = "lower"
        } else {
            message = "Yes! The secret number is ${secretNumber.secret}"
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message).setPositiveButton("OK", null)
            .show()
    }
}