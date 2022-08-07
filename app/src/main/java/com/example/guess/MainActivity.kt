package com.example.guess

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_CAMERA: Int = 1
    val TAG = MainActivity::class.java.simpleName
    var intentService : Intent? = null

    val function = listOf<String>(
        "Camera",
        "Guess game",
        "Record list",
        "Snooker Records",
        "News",
        "Maps"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val TAG = MainActivity::class.java.simpleName

        // Recycler View
        recycler_record_list.layoutManager = LinearLayoutManager(this)
        recycler_record_list.setHasFixedSize(true)
        recycler_record_list.adapter = FunctionAdapter()

        //spinner
        val spinnerData = arrayOf("Blue", "Gray", "pink", "purple")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
        spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.d(TAG, "OnItemSelectedListener: ${spinnerData[position]}")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    inner class FunctionAdapter() : RecyclerView.Adapter<FuntionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuntionHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_function, parent, false)
            val holder = FuntionHolder(view)
            return holder
        }

        override fun onBindViewHolder(holder: FuntionHolder, position: Int) {
            holder.nameText.text = function.get(position)
            holder.itemView.setOnClickListener {
                functionClicked(position)
            }
        }

        override fun getItemCount(): Int {
            return function.size
        }

    }

    fun functionClicked(position: Int) {
        when (position) {
            0 -> {
                val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    takePhoto()
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_CAMERA)
                }
            }
            1 -> startActivity(Intent(this, MaterialActivity::class.java))
            2 -> startActivity(Intent(this, RecordListActivity::class.java))
            3 -> startActivity(Intent(this, SnookerActivity::class.java))
            else -> return
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PackageManager.PERMISSION_GRANTED) {
            takePhoto()
        } else {
            Toast.makeText(this, "Please open the permission", Toast.LENGTH_SHORT).show()
        }
    }

    private fun takePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent)
    }

    class FuntionHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView = view.name
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_catch) {
            Log.d(TAG, "catch selected")
            intentService = Intent(this, CatchService::class.java)
            startService(intentService)
            // 以下測試用
            startService(Intent(this, CatchService::class.java))
            startService(Intent(this, CatchService::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onStop() {
//        super.onStop()
//        stopService(intentService)
//    }
}