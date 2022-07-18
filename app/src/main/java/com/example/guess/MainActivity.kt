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
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Secret number is: " + secretNumber.secret)
    }

    fun check(view: View) {
        val n = ed_number.text.toString().toInt()
        Log.d(TAG, "number: " + n)
        val validate = secretNumber.validate(n)
        val message:String
        if (validate > 0) {
            message = getString(R.string.bigger)
        } else if (validate < 0) {
            message = getString(R.string.lower)
        } else {
            message = getString(R.string.yes_you_got_it) + secretNumber.secret
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.Dialog_title))
            .setMessage(message).setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}