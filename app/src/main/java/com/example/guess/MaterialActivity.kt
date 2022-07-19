package com.example.guess

import android.content.DialogInterface
import android.content.Intent
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
import com.example.guess.databinding.ActivityMaterialBinding
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
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

     binding = ActivityMaterialBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay the game")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes") { dialog, which ->
                    viewModel.reset()
                }
                .setNeutralButton("Cancel", null)
                .show()
        }

    }

    fun check(view: View) {
        if (ed_number.text.toString().isEmpty()) {
            Toast.makeText(this, R.string.ed_number_is_empty, Toast.LENGTH_LONG).show()
        } else {
            viewModel.guess(ed_number.text.toString().toInt())
            ed_number.setText("")
        }
        viewModel.result.observe(this, Observer { result ->
            val message = when (result) {
                Result.YES -> R.string.yes_you_got_it
                Result.BIGGER -> R.string.bigger
                else -> R.string.lower
            }
            AlertDialog.Builder(this)
                .setTitle(R.string.Dialog_title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, { dialog, which ->
                    if (result == Result.YES) {
                        val intent = Intent(this, RecordActivity::class.java)
                        intent.putExtra("COUNTER", viewModel.counter.value)
                        startActivity(intent)
                    }
                }
            )
                .show()
        })
//        val n = ed_number.text.toString().toInt()
//        Log.d(TAG, "number: " + n)
//        val validate = secretNumber.validate(n)
//        val message:String
//        if (validate > 0) {
//            message = getString(R.string.bigger)
//        } else if (validate < 0) {
//            message = getString(R.string.lower)
//        } else {
//            message = getString(R.string.yes_secret_number_is) + secretNumber.secret
//        }
//        counter.setText(secretNumber.count.toString())
//        ed_number.setText("")
////        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//        AlertDialog.Builder(this)
//            .setTitle(getString(R.string.Dialog_title))
//            .setMessage(message).setPositiveButton(getString(R.string.ok), null)
//            .show()
    }
}