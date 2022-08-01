package com.example.guess

import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Database
import androidx.room.Room
import com.example.guess.data.GameDatabase
import com.example.guess.data.Record
import com.example.guess.databinding.ActivityMaterialBinding
import kotlinx.android.synthetic.main.content_material.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

class MaterialActivity : AppCompatActivity() {
    private val REQUEST_RECORD = 100
    private lateinit var viewModel: GuessViewModel
    val TAG = MaterialActivity::class.java.simpleName

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java)
        viewModel.counter.observe(this, Observer { data ->
            counter.setText(data.toString())
        })
        Log.d(TAG, "secret number is: ${viewModel.secret}")

     binding = ActivityMaterialBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            replay()
        }
    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle("Replay the game")
            .setMessage("Are you sure ?")
            .setPositiveButton("Yes") { dialog, which ->
                viewModel.reset()
            }
            .setNeutralButton("Cancel", null)
            .show()
    }

    fun check(view: View) {
        if (ed_number.text.toString().isEmpty()) {
            Toast.makeText(this, R.string.ed_number_is_empty, Toast.LENGTH_LONG).show()
        } else {
            viewModel.guess(ed_number.text.toString().toInt())
            ed_number.setText("")
        }
        val message = when (viewModel.result) {
            Result.YES -> R.string.yes_you_got_it
            Result.BIGGER -> R.string.bigger
            else -> R.string.lower
        }
        AlertDialog.Builder(this)
            .setTitle(R.string.Dialog_title)
            .setMessage(message)
            .setPositiveButton(R.string.ok, { dialog, which ->
                dialog.cancel()
                if (viewModel.result == Result.YES) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", viewModel.counter.value)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            })
            .show()
    }

    fun test() {
        val intent = Intent(this, RecordActivity::class.java)
        intent.putExtra("AA", "123")
        intent.putExtra("BB", "456")
        startActivity(intent)

        Intent(this, RecordActivity::class.java).apply {
            putExtra("aa", "123")
            putExtra("bb", "456")
        }.also {
            startActivity(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RECORD) {
            if (resultCode == RESULT_OK) {
                val nickname = data?.getStringExtra("NICKNAME")
                Log.d(TAG, "onActivityResult: $nickname")
                replay()
            }
        }
    }


}